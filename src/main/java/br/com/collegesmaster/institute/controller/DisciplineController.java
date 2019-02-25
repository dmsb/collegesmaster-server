package br.com.collegesmaster.institute.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.institute.model.service.DisciplineService;

@RestController
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;
	
	@GetMapping("/disciplines")
	@ResponseBody
	public ResponseEntity<Iterable<DisciplineImpl>> disciplineAutocomplete(final Predicate predicate, final Sort sort) {
		final Iterable<DisciplineImpl> disciplines = disciplineService.findByPredicate(predicate, sort);
		return new ResponseEntity<Iterable<DisciplineImpl>>(disciplines, null, HttpStatus.OK);
	}
}
