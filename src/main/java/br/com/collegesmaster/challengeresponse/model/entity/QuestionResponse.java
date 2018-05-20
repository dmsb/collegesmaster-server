package br.com.collegesmaster.challengeresponse.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.enums.Letter;
import br.com.collegesmaster.challengeresponse.model.entity.impl.QuestionResponseImpl;
import br.com.collegesmaster.generics.model.Model;

@JsonDeserialize(as = QuestionResponseImpl.class)
public interface QuestionResponse extends Model {

	void setTargetQuestion(Question targetQuestion);

	Question getTargetQuestion();

	void setChallengeResponse(ChallengeResponse challengeResolution);

	ChallengeResponse getChallengeResponse();

	void setLetter(Letter letter);

	Letter getLetter();

}
