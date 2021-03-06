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
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QChallengeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;
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
	
	@PreAuthorize("hasAnyAuthority('CREATE_CHALLENGE')")
	@Transactional
	@Override
	public Challenge create(final Challenge challenge) {
		return challengeRepository.save((ChallengeImpl)challenge);
	}
	
	@PreAuthorize("hasAnyAuthority('UPDATE_CHALLENGE')")
	@Transactional
	@Override
	public Challenge update(final Challenge challenge) {
		final List<QuestionImpl> savedQuestions = this.questionService.saveAll(challenge.getQuestions());
		challenge.setQuestions(savedQuestions);
		return challengeRepository.save((ChallengeImpl)challenge);
	}
	
	@PreAuthorize("hasAnyAuthority('DELETE_CHALLENGE')")
	@Transactional	
	@Override
	public void deleteById(final Integer id) {
		this.challengeRepository.deleteById(id);
	}

	@PreAuthorize("hasAnyAuthority('READ_CHALLENGE')")
	@Transactional(readOnly = true)
	public Challenge findById(Integer id) {
		return this.challengeRepository
					.findById(id)
					.orElse(null);
	}
	
	@PreAuthorize("hasAnyAuthority('READ_CHALLENGE')")
	@Transactional(readOnly = true)
	@Override
	public List<Challenge> findByUser(final User user) {
		return this.challengeRepository.findByOwner(user);
	}
	
	@PreAuthorize("hasAnyAuthority('READ_CHALLENGE')")
	@Transactional(readOnly = true)
	@Override
	public Iterable<ChallengeImpl> findByPredicate(final Predicate predicate) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		
		booleanBuilderQuery.and(QChallengeImpl.challengeImpl.owner.username.eq(loggedUsername));
		return this.challengeRepository.findAll(booleanBuilderQuery.getValue());
	}

	@PreAuthorize("hasAnyAuthority('READ_CHALLENGE')")
	@Transactional(readOnly = true)
	@Override
	public Page<ChallengeImpl> findByPredicate(Predicate predicate, Pageable pageable) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		
		booleanBuilderQuery.and(QChallengeImpl.challengeImpl.owner.username.eq(loggedUsername));
		return this.challengeRepository.findAll(booleanBuilderQuery.getValue(), pageable);
	}
}
