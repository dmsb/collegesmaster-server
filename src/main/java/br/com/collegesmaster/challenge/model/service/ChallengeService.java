package br.com.collegesmaster.challenge.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.security.model.entity.User;

@Repository
public interface ChallengeService extends GenericCRUD<Challenge> {
	
	Challenge findById(final Integer id);
	
	List<Question> findQuestionsByChallenge(Challenge selectedChallenge);
	
	List<Challenge> findByUser(User user);
	
	Iterable<ChallengeImpl> findByPredicate(final Predicate predicate);
	
	Page<ChallengeImpl> findByPredicate(final Predicate predicate, Pageable pageable);
}
