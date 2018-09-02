package br.com.collegesmaster.security.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.security.model.entity.Role;
import br.com.collegesmaster.security.model.service.RoleService;

@RestController("roles/")
public class RoleController {

	@Inject
	private RoleService roleService;
	
	@GetMapping("lists/")
	public Iterable<Role> test(Predicate predicate) {
		return roleService.findAll(predicate);
	}
}
