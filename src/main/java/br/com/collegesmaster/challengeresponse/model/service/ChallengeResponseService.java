package br.com.collegesmaster.challengeresponse.model.service;

import java.util.List;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.security.model.entity.User;

public interface ChallengeResponseService extends GenericCRUD<ChallengeResponse> {
	
	ChallengeResponse findById(final Integer id);
	
	List<ChallengeResponse> findByUser(User user);
	
	List<ChallengeResponse> findByChallenge(Challenge selectedChallenge);

}
