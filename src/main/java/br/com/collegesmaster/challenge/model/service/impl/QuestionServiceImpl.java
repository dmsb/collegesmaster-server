package br.com.collegesmaster.challenge.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.impl.QQuestionImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;
import br.com.collegesmaster.challenge.model.repository.QuestionRepository;
import br.com.collegesmaster.challenge.model.service.QuestionService;
import br.com.collegesmaster.generics.facade.AuthenticationFacade;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Override
	public Page<QuestionImpl> findQuestions(Predicate predicate, Pageable pageable) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		
		booleanBuilderQuery.and(QQuestionImpl.questionImpl.challenge.user.username.eq(loggedUsername));
		return this.questionRepository.findAll(booleanBuilderQuery.getValue(), pageable);
	}

}
