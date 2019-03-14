package br.com.collegesmaster.challengeresponse.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.challengeresponse.model.entity.impl.ChallengeResponseImpl;
import br.com.collegesmaster.challengeresponse.model.repository.ChallengeResponseRepository;
import br.com.collegesmaster.challengeresponse.model.service.ChallengeResponseService;
import br.com.collegesmaster.challengeresponse.model.service.RankingService;
import br.com.collegesmaster.security.model.entity.User;

@Service("challengeReponseService")
public class ChallengeResponseServiceImpl implements ChallengeResponseService {
	
	@Autowired
	private ChallengeResponseRepository challengeResponseRepository;
	
	@Autowired
	private RankingService rankingBusiness;
	
	@PreAuthorize("hasAnyAuthority('STUDENT', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public ChallengeResponse create(final ChallengeResponse response) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		final User result = challengeResponseRepository.repliedByUser(user);
		if(result == null) {
			final ChallengeResponse createdResponse = challengeResponseRepository.save((ChallengeResponseImpl)response);
			if(createdResponse != null) {
				rankingBusiness.addScoreToUser(response);
			}
			return createdResponse;
		} 
		return null;
	}
	
	@PreAuthorize("hasAuthority('STUDENT', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public ChallengeResponse update(final ChallengeResponse response) {
		return challengeResponseRepository.save((ChallengeResponseImpl)response);
	}
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@Transactional
	@Override
	public Boolean deleteById(final Integer id) {
		challengeResponseRepository.deleteById(id);
		return Boolean.TRUE;
	}
	
	@PreAuthorize("hasAuthority('STUDENT', 'PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public ChallengeResponse findById(final Integer id) {
		return challengeResponseRepository
					.findById(id)
					.orElse(null);
	}
	
	@PreAuthorize("hasAuthority('STUDENT', 'PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public List<ChallengeResponse> findByUser(final User user) {
		return challengeResponseRepository.findByUser(user);
		
	}
	
	@PreAuthorize("hasAuthority('PROFESSOR', 'ADMINISTRATOR')")
	@Transactional
	@Override
	public List<ChallengeResponse> findByChallenge(Challenge selectedChallenge) {
		return challengeResponseRepository.findByChallenge(selectedChallenge);
	}

}
