package study.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import study.spring.service.ReplyService;
import study.spring.vo.ReplyVO;

@RestController
@RequestMapping("/replies/*")
public class ReplyController {
	@Autowired
	private ReplyService service;
	
	@PostMapping(value="/new", consumes ="application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public void register(@RequestBody ReplyVO replyVO, @RequestParam int page) {
		service.register(replyVO);
	}
	@GetMapping(value="/selectR", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyVO> selectR(@RequestParam("rnum") int rnum){
		return new ResponseEntity<>(service.selectReply(rnum),HttpStatus.OK);
	}

	@PostMapping(value="/updateR", consumes = "application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	public void postUpdateR(@RequestBody ReplyVO replyVO) {
		service.update(replyVO);
	}
	@DeleteMapping("/deleteR")
	public void deleteR(@RequestParam int rnum) {
		service.delete(rnum);
	}
}
