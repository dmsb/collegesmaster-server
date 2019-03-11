package br.com.collegesmaster.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challenge.model.service.AlternativeService;

@RestController
public class AlternativeController {

	@Autowired
	private AlternativeService alternativeService;
	
	@GetMapping("/challenges/questions/alternatives")
	@ResponseBody
	public ResponseEntity<Iterable<AlternativeImpl>> findAlternatives(
			@QuerydslPredicate(root = AlternativeImpl.class) final Predicate predicate) {
		final Iterable<AlternativeImpl> result = this.alternativeService.findAlternatives(predicate);
		return new ResponseEntity<Iterable<AlternativeImpl>>(result, null, HttpStatus.OK);
	}
}
