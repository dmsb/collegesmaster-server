package br.com.collegesmaster.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {
	
	@GetMapping("/test")
	public String test() {
		return "Hello buddy!";
	}
}
