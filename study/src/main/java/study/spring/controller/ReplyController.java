package study.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import study.spring.service.ReplyService;
import study.spring.vo.ReplyVO;

@RestController
@RequestMapping("/replies")
@SessionAttributes("reply")
public class ReplyController {
	@Autowired
	private ReplyService service;
	@PostMapping(value = "/new", consumes ="application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public void register(@RequestBody ReplyVO replyVO) {
		service.register(replyVO);
	}
	@GetMapping("/list")
	public String list(@RequestParam("num") int num,@RequestParam("page") int page, Model model) {
		model.addAttribute("reply",service.selectReplyList(num));
		return "redirect:/lookie/board/select?num="+num+"&page="+page;
	}
}
