package br.com.collegesmaster.challengeresponse.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.ChallengeResponseImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.QuestionResponseImpl;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.security.model.entity.User;

@JsonDeserialize(as = ChallengeResponseImpl.class)
public interface ChallengeResponse extends Model {

	void setPunctuation(Integer punctuation);

	Integer getPunctuation();

	void setQuestionsResponse(Collection<QuestionResponseImpl> myQuestionsResolution);

	Collection<QuestionResponseImpl> getQuestionsResponse();

	void setUser(User owner);

	User getUser();

	void setChallenge(Challenge targetChallenge);

	Challenge getChallenge();

	void addPunctuation(final QuestionResponse response, AlternativeImpl alternative);

	void calculatePunctuation();

}
