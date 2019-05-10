package br.com.collegesmaster.challenge.model.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;

public interface QuestionService {
	
	Page<QuestionImpl> findQuestions(final Predicate predicate, Pageable pageable);

	List<QuestionImpl> saveAll(Collection<QuestionImpl> questions);
	
}
