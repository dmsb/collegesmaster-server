package br.com.collegesmaster.security.controller;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.service.UserService;

@RestController("users/")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Inject
	private UserService userService;
	
	@GetMapping("/")
	public String test() {
		logger.info("Logger's work's!!");
		return "Hello buddy!";
	}
	
	@PostMapping("create/")
	public User create(final User user) {
		return userService.create(user);
	}
}
