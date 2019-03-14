package br.com.collegesmaster.challenge.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challenge.model.service.ChallengeService;

@RestController
public class ChallengeController {

	@Autowired
	private ChallengeService challengeService;
	
	@GetMapping("/challenges")
	@ResponseBody
	public ResponseEntity<Page<ChallengeImpl>> findChallenges(
			@QuerydslPredicate(root = ChallengeImpl.class) final Predicate predicate, Pageable pageable) {
		final Page<ChallengeImpl> result = this.challengeService.findByPredicate(predicate, pageable);
		return new ResponseEntity<Page<ChallengeImpl>>(result, null, HttpStatus.OK);
	}
	
	@PutMapping("/challenges/{id}")
	@ResponseBody
	public ResponseEntity<Challenge> update(@RequestBody Challenge challenge, @PathParam("id") Long id) {
		final Challenge updatedChallenge = this.challengeService.update(challenge);
		return new ResponseEntity<Challenge>(updatedChallenge, null, HttpStatus.OK);
	}
}
