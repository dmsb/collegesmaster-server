package br.com.collegesmaster.security.controller;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
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
	
	@GetMapping("/users/logged_user_authorities")
	public ResponseEntity<Collection<? extends GrantedAuthority>> findLoggedUserAuthorities() {
		logger.info("Logger's work's!!");
		final Collection<? extends GrantedAuthority> authorities = this.userService.getLoggedUserAuthorities();
		return new ResponseEntity<Collection<? extends GrantedAuthority>>(authorities, HttpStatus.OK);
	}
	
	@PostMapping("/users/create")
	public User create(final User user) {
		return userService.create(user);
	}
}
