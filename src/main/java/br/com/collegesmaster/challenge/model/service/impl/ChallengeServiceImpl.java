package br.com.collegesmaster.challenge.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challenge.model.repository.ChallengeRepository;
import br.com.collegesmaster.challenge.model.service.ChallengeService;
import br.com.collegesmaster.challenge.model.service.QuestionService;
import br.com.collegesmaster.security.model.entity.User;

@Service("challengeService")
public class ChallengeServiceImpl implements ChallengeService {
	
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
	public Iterable<ChallengeImpl> findByPredicate(final Predicate predicate) {
		return this.challengeRepository.findAll(predicate);
	}
}
