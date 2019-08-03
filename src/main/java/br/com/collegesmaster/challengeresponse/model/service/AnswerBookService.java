package br.com.collegesmaster.challengeresponse.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challengeresponse.model.entity.AnswerBook;
import br.com.collegesmaster.challengeresponse.model.entity.impl.AnswerBookImpl;
import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.security.model.entity.User;

public interface AnswerBookService extends GenericCRUD<AnswerBook> {
	
	AnswerBook findById(final Integer id);
	
	List<AnswerBook> findByUser(final User user);
	
	List<AnswerBook> findByChallenge(final Challenge selectedChallenge);

	Page<AnswerBookImpl> findByPredicate(final Predicate predicate, final Pageable pageable);

}
