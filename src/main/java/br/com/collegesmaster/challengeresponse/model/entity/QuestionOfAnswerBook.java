package br.com.collegesmaster.challengeresponse.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.QuestionOfAnswerBookImpl;
import br.com.collegesmaster.generics.model.Model;

@JsonDeserialize(as = QuestionOfAnswerBookImpl.class)
public interface QuestionOfAnswerBook extends Model {

	void setTargetQuestion(Question targetQuestion);

	Question getTargetQuestion();

	void setChallengeResponse(AnswerBook challengeResolution);

	AnswerBook getChallengeResponse();

	void setSelectedAlternatives(Collection<AlternativeImpl> selectedAlternatives);

	Collection<AlternativeImpl> getSelectedAlternatives();

}
