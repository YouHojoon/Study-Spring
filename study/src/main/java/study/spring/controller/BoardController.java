package study.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import study.spring.service.BoardService;
import study.spring.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list",service.selectBoardList());
	}
	@GetMapping("/register")
	public void getRegister(){}
	@PostMapping("/register")
	public String register(BoardVO boardVO, RedirectAttributes r) {
		service.register(boardVO);
		r.addFlashAttribute("result",boardVO.getNum());
		return "redirect:/board/list";
	}
	@GetMapping("/select")
	public void select(@RequestParam("num") int num, Model model) {
		model.addAttribute("board",service.selectBoard(num));
	}
	@PostMapping("/update")
	public String update(BoardVO boardVO,RedirectAttributes r) {
		try {
			service.update(boardVO);
			r.addFlashAttribute("result", "success");
		}catch(Exception e) {r.addFlashAttribute("result","fail");}
		return "redirect:/board/list";
	}
	@PostMapping("/delete")
	public String delete(@RequestParam("num") int num,RedirectAttributes r) {
		try {
			service.delete(num);
			r.addFlashAttribute("result", "success");
		}catch(Exception e) {r.addFlashAttribute("result","fail");}
		return "redirect:/board/list";
	}
}
