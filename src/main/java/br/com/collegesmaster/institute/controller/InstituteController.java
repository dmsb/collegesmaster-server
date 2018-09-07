package br.com.collegesmaster.institute.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.collegesmaster.institute.model.entity.Institute;
import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;
import br.com.collegesmaster.institute.model.service.InstituteService;
import br.com.collegesmaster.security.controller.UserController;

@RestController
public class InstituteController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private InstituteService instituteService;
	
	@GetMapping("/institutes")
	public Iterable<InstituteImpl> list() {
		logger.info("listing institutes");
		return instituteService.findAll();
	}
	
	@PostMapping("/institutes/create")
	public ResponseEntity<Institute> create(@RequestBody final InstituteImpl institute) {
		final Institute insituteCreated = instituteService.create(institute);
		final ResponseEntity<Institute> response = new ResponseEntity<>(insituteCreated, HttpStatus.OK);
		return response;
	}
}
