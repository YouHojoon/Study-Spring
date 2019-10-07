package study.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.spring.dto.BoardPageDTO;
import study.spring.dto.PageDTO;
import study.spring.service.BoardService;
import study.spring.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
@SessionAttributes({"serchList","keyword","type"})
public class BoardController {
	@Autowired
	private BoardService service;
	@GetMapping("/list")
	public void list(@RequestParam("page") int page,Model model) {
		model.addAttribute("list",service.selectBoardList(page));
		model.addAttribute("pageMaker",new PageDTO(page, service.totoalCount(new BoardPageDTO(page))));
	}
	@PostMapping("/list")
	public void postList(@RequestParam("page") int page,
			@RequestParam("type") String type, @RequestParam("keyword") String keyword,Model model) {
		BoardPageDTO boardPageDTO=new BoardPageDTO(page,type,keyword);
		model.addAttribute("serchList",service.selectBoardListSerch(boardPageDTO));	
		model.addAttribute("pageMaker",new PageDTO(page, service.totoalCount(boardPageDTO)));
		model.addAttribute("type",type);
		model.addAttribute("keyword",keyword);
	}
	@GetMapping("/register")
	public void getRegister(@RequestParam("page") int page,Model model){
		model.addAttribute("page",page);
	}
	@PostMapping("/register")
	public String register(BoardVO boardVO,@RequestParam("page") int page,RedirectAttributes r) {
		service.register(boardVO);
		r.addFlashAttribute("result",boardVO.getNum());
		return "redirect:/board/list?page="+page;
	}
	@GetMapping({"/select","/update"})
	public void select(@RequestParam("num") int num,@RequestParam("page") int page ,Model model) {
		model.addAttribute("board",service.selectBoard(num));
		model.addAttribute("page", page);
	}
	
	@PostMapping("/update")
	public String update(BoardVO boardVO,@RequestParam("page") int page,RedirectAttributes r) {
		try {
			service.update(boardVO);
			r.addFlashAttribute("result", "success");
		}catch(Exception e) {r.addFlashAttribute("result","fail");}
		return "redirect:/board/list?page="+page;
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("num") int num,@RequestParam("page") int page
			,RedirectAttributes r) {
		try {
			service.delete(num);
			r.addFlashAttribute("result", "success");
		}catch(Exception e) {r.addFlashAttribute("result","fail");}
		return "redirect:/board/list?page="+page;
	}
	@ExceptionHandler({MethodArgumentTypeMismatchException.class,
		MissingServletRequestParameterException.class})
	public String exceptionHandler() {
		return "redirect:/board/list?page=1";
	}
}
