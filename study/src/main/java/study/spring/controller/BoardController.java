package study.spring.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@ExceptionHandler({MethodArgumentTypeMismatchException.class,
		MissingServletRequestParameterException.class})
	public String exceptionHandler() {
		return "redirect:/board/list?page=1";
	}
}
