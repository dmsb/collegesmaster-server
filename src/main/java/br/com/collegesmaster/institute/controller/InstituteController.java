package br.com.collegesmaster.institute.controller;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;
import br.com.collegesmaster.institute.model.service.InstituteService;
import br.com.collegesmaster.security.controller.UserController;

@RestController
public class InstituteController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Inject
	private InstituteService instituteService;
	
	@GetMapping("/institutes")
	public Iterable<InstituteImpl> list() {
		logger.info("listing institutes");
		return instituteService.findAll();
	}
}
