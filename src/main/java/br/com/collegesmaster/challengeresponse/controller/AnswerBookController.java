package br.com.collegesmaster.challengeresponse.controller;

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

import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.AnswerBookImpl;
import br.com.collegesmaster.challengeresponse.model.service.AnswerBookService;

@RestController
public class AnswerBookController {
	
	@Autowired
	private AnswerBookService answerBookService;
	
	@GetMapping("/answer_books_by_logged_student")
	@ResponseBody
	public ResponseEntity<Page<AnswerBookImpl>> findAnswerBooksByLoggedStudent(
			@QuerydslPredicate(root = ChallengeImpl.class) final Predicate predicate, Pageable pageable) {
		final Page<AnswerBookImpl> answeredChallenges = this.answerBookService.findByPredicate(predicate, pageable);
		return new ResponseEntity<Page<AnswerBookImpl>>(answeredChallenges, null, HttpStatus.OK);
	}
}
