package br.com.collegesmaster.challenge.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QChallengeImpl;
import br.com.collegesmaster.challenge.model.repository.ChallengeRepository;
import br.com.collegesmaster.challenge.model.service.ChallengeService;
import br.com.collegesmaster.challenge.model.service.QuestionService;
import br.com.collegesmaster.generics.facade.AuthenticationFacade;
import br.com.collegesmaster.security.model.entity.User;

@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@PreAuthorize("hasAnyAuthority('PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public Challenge create(final Challenge challenge) {
		return challengeRepository.save((ChallengeImpl)challenge);
	}
	
	@PreAuthorize("hasAnyAuthority('PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public Challenge update(final Challenge challenge) {
		return challengeRepository.save((ChallengeImpl)challenge);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
	@Transactional
	@Override
	public Boolean deleteById(final Integer id) {
		challengeRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@PreAuthorize("hasAnyAuthority('STUDENT', 'PROFESSOR', 'ADMINISTRATOR')")
	@Transactional(readOnly = true)
	public Challenge findById(Integer id) {
		return challengeRepository
					.findById(id)
					.orElse(null);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Question> findQuestionsByChallenge(final Challenge selectedChallenge) {
		return questionService.findByChallenge(selectedChallenge);
	}
	
	@PreAuthorize("hasAnyAuthority('PROFESSOR', 'ADMINISTRATOR')")
	@Transactional(readOnly = true)
	@Override
	public List<Challenge> findByUser(final User user) {
		return challengeRepository.findByUser(user);
	}
	
	@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'PROFESSOR' )")
	@Transactional(readOnly = true)
	@Override
	public Iterable<ChallengeImpl> findByPredicate(final Predicate predicate) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		
		booleanBuilderQuery.and(QChallengeImpl.challengeImpl.user.username.eq(loggedUsername));
		return this.challengeRepository.findAll(booleanBuilderQuery.getValue());
	}

	@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'PROFESSOR' )")
	@Transactional(readOnly = true)
	@Override
	public Page<ChallengeImpl> findByPredicate(Predicate predicate, Pageable pageable) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		
		booleanBuilderQuery.and(QChallengeImpl.challengeImpl.user.username.eq(loggedUsername));
		return this.challengeRepository.findAll(booleanBuilderQuery.getValue(), pageable);
	}
}
