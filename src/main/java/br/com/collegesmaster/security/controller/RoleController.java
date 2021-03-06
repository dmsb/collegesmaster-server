package br.com.collegesmaster.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.security.model.entity.Role;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;
import br.com.collegesmaster.security.model.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/roles")
	public Iterable<RoleImpl> list(Predicate predicate) {
		return this.roleService.findAll(predicate);
	}
	
	@PostMapping("/roles/create")
	public ResponseEntity<Role> create(@RequestBody final Role role) {
		final Role roleCreated = this.roleService.create(role);
		final ResponseEntity<Role> response = new ResponseEntity<>(roleCreated, HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/roles/update/{id}")
	public ResponseEntity<Role> update(@RequestBody final Role role) {
		final Role roleUpdated = this.roleService.update(role);
		final ResponseEntity<Role> response = new ResponseEntity<>(roleUpdated, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/roles/remove/{id}")
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		this.roleService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
