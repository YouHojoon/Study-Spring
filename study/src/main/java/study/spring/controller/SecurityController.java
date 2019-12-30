package study.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security/*")
public class SecurityController {
	@GetMapping("/all")
	public void all() {
		
	}
	@GetMapping("/member")
	public void member() {
		
	}
	@GetMapping("/admin")
	public void admin(){
		
	}
}
