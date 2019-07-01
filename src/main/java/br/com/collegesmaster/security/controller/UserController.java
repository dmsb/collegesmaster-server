package br.com.collegesmaster.security.controller;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.service.UserService;

@RestController
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/logged_user_roles")
	public ResponseEntity<Collection<String>> findLoggedUserRoles() {
		logger.info("Logger's work's!!");
		final Collection<String> roles = this.userService.getLoggedUserRoles();
		return new ResponseEntity<Collection<String>>(roles, HttpStatus.OK);
	}
	
	@PostMapping("/users/create")
	public User create(final User user) {
		return this.userService.create(user);
	}
}
