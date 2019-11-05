package study.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import study.spring.service.ReplyService;
import study.spring.vo.ReplyVO;

@Controller
@RequestMapping("/replies/*")
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	@PostMapping(value="/new", consumes ="application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public void register(@RequestBody ReplyVO replyVO) {
		service.register(replyVO);
	}
	
	@GetMapping("/updateR")
	public void updateR(@RequestParam int rnum,@RequestParam int page, Model model) {
		model.addAttribute("replySelect",service.selectReply(rnum));
		model.addAttribute("page",page);	
	}
	
	@PostMapping(value="/updateR", consumes = "application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public void postUpdateR(@RequestBody ReplyVO replyVO) {
		service.update(replyVO);
	}
}
