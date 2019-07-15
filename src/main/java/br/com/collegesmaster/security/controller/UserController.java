package br.com.collegesmaster.security.controller;

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
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/logged_user_roles")
	public <T extends User> ResponseEntity<T> findLoggedUserRoles() {
		final T roles = this.userService.getLoggedUser();
		return new ResponseEntity<T>(roles, HttpStatus.OK);
	}
	
	@PostMapping("/users/create")
	public User create(final User user) {
		return this.userService.create(user);
	}
}
