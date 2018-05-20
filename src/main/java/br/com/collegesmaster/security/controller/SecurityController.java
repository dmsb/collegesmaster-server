package br.com.collegesmaster.security.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	private static final Logger logger = LogManager.getLogger(SecurityController.class);
	
	@GetMapping("/")
	public String test() {
		logger.info("Logger's work's!!");
		return "Hello buddy!";
	}
}
