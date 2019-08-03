package br.com.collegesmaster.challengeresponse.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.AnswerBookImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.QuestionOfAnswerBookImpl;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.security.model.entity.Student;

@JsonDeserialize(as = AnswerBookImpl.class)
public interface AnswerBook extends Model {

	void setScore(Integer score);

	Integer getScore();

	void setQuestionOfAnswerBook(Collection<QuestionOfAnswerBookImpl> questionsOfAnswerbook);

	Collection<QuestionOfAnswerBookImpl> getQuestionsOfAnswerBook();

	void setOwner(Student owner);

	Student getOwner();

	void setChallenge(Challenge targetChallenge);

	Challenge getChallenge();

	void addScore(final QuestionOfAnswerBook response, AlternativeImpl alternative);

	void calculateScore();

}
