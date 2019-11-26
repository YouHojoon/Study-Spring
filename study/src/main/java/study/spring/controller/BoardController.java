package study.spring.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.coobird.thumbnailator.Thumbnailator;
import study.spring.dto.AttachFileDTO;
import study.spring.dto.BoardPageDTO;
import study.spring.dto.PageDTO;
import study.spring.dto.RpageDTO;
import study.spring.service.BoardService;
import study.spring.service.ReplyService;
import study.spring.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
@SessionAttributes({"serchList","keyword","type"})
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private ReplyService rservice;
	private static final Logger log=LoggerFactory.getLogger(BoardController.class);
	@GetMapping("/test")
	public void test() {}
	@GetMapping("/list")
	public void list(@RequestParam("page") int page,Model model,HttpSession session) {
		BoardPageDTO boardPageDTO=null;
		if(session.getAttribute("keyword")!=null&&session.getAttribute("type")!=null) {
			log.info((String)session.getAttribute("keyword"));
			boardPageDTO=new BoardPageDTO(page,(String)session.getAttribute("type"),(String)session.getAttribute("keyword"));
		}
		else
			boardPageDTO=new BoardPageDTO(page);
		model.addAttribute("serchList",service.selectBoardListSerch(boardPageDTO));	
		model.addAttribute("pageMaker",new PageDTO(page, service.totoalCount(boardPageDTO)));
	}
	@PostMapping("/list")
	public void postList(@RequestParam("type") String type, @RequestParam("keyword") String keyword,Model model) {
		BoardPageDTO boardPageDTO=new BoardPageDTO(1,type,keyword);
		model.addAttribute("serchList",service.selectBoardListSerch(boardPageDTO));	
		model.addAttribute("pageMaker",new PageDTO(1, service.totoalCount(boardPageDTO)));
		model.addAttribute("type",type);
		model.addAttribute("keyword",keyword);
	}
	@GetMapping("/register")
	public void getRegister(@RequestParam("page") int page,Model model){
		model.addAttribute("page",page);
	}
	@PostMapping("/register")
	public String register(BoardVO boardVO,RedirectAttributes r) {
		service.register(boardVO);
		r.addFlashAttribute("result",1);
		return "redirect:/board/list?page=1";
	}
	@GetMapping("/select")
	public void select(@RequestParam("num") int num,@RequestParam("page") int page ,
			@RequestParam("rpage") int rpage, Model model) {
		model.addAttribute("board",service.selectBoard(num));
		model.addAttribute("page", page);
		model.addAttribute("reply",rservice.selectReplyList(new RpageDTO(num,rpage)));//댓글때문에 여기로
		model.addAttribute("rpageMaker",new PageDTO(rpage, rservice.totalCount(num)));
	}
	@GetMapping("/update")
	public void update(@RequestParam("num") int num,@RequestParam("page") int page , Model model) {
		model.addAttribute("board",service.selectBoard(num));
		model.addAttribute("page", page);
	}
	@PostMapping("/update")
	public String update(BoardVO boardVO,RedirectAttributes r) {
		service.update(boardVO);
		r.addFlashAttribute("result", "update");
		return "redirect:/board/list?page=1";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("num") int num,RedirectAttributes r) {
		service.delete(num);
		r.addFlashAttribute("result", "delete");
		return "redirect:/board/list?page=1";
	}
	@GetMapping("/upload")
	public void upload() {}
	@PostMapping("/upload")
	public ModelAndView upload(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list= new ArrayList<AttachFileDTO>();
		String uploadFolder ="C:\\Users\\USER\\Desktop\\temp";
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date= new Date();
		String str=sdf.format(date);
		str.replace("-", File.separator);
		File uploadPath=new File(uploadFolder, str);
		if(!uploadPath.exists())uploadPath.mkdirs();
		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachFileDTO= new AttachFileDTO();
			String uploadFileName=multipartFile.getOriginalFilename();
			uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			attachFileDTO.setFileName(uploadFileName);
			UUID uuid=UUID.randomUUID();
			attachFileDTO.setUuid(uuid.toString());
			attachFileDTO.setUploadPath(uploadPath.toPath().toString());
			uploadFileName=uuid.toString()+"_"+uploadFileName;
			File saveFile = new File(uploadPath,uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
				attachFileDTO.setImage(Files.probeContentType(saveFile.toPath()).startsWith("image"));
				if(attachFileDTO.isImage()) {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					thumbnail.close();
				}
				list.add(attachFileDTO);
			}catch(Exception e) {log.error(e.getMessage());}
			
		}
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName("board/upload");
		modelAndView.addObject("uploadList", list);
		return modelAndView;
	}
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		File file= new File("C:\\Users\\USER\\Desktop\\temp"+fileName);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders httpHeaders= new HttpHeaders();
			httpHeaders.add("Content-Type", Files.probeContentType(file.toPath()));
			result= new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),httpHeaders,HttpStatus.OK);
		}catch(Exception e) {e.printStackTrace();}
		return result;
	}
	@ExceptionHandler({MethodArgumentTypeMismatchException.class,
		MissingServletRequestParameterException.class})
	public String exceptionHandler() {
		return "redirect:/board/list?page=1";
	}
}
