package br.com.collegesmaster.challengeresponse.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challengeresponse.model.entity.AnswerBook;
import br.com.collegesmaster.challengeresponse.model.entity.impl.AnswerBookImpl;
import br.com.collegesmaster.security.model.entity.User;

@Repository
public interface AnswerBookRepository extends JpaRepository<AnswerBookImpl, Integer>, 
QuerydslPredicateExecutor<AnswerBookImpl> {
	
	List<AnswerBook> findByOwner(User owner);
	
	List<AnswerBook> findByChallenge(Challenge selectedChallenge);
	
	List<AnswerBook> findByChallengeAndOwner(Challenge challenge, User owner);
}
