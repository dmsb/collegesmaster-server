package br.com.collegesmaster.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;
import br.com.collegesmaster.challenge.model.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/challenges/questions")
	@ResponseBody
	public ResponseEntity<Page<QuestionImpl>> findQuestions(
			@QuerydslPredicate(root = QuestionImpl.class) final Predicate predicate, Pageable pageable) {
		final Page<QuestionImpl> challengeQuestions = this.questionService.findQuestions(predicate, pageable);
		return new ResponseEntity<Page<QuestionImpl>>(challengeQuestions, null, HttpStatus.OK);
	}
	
}
