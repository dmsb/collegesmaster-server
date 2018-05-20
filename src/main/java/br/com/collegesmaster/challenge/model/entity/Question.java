package br.com.collegesmaster.challenge.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;
import br.com.collegesmaster.generics.model.Model;

@JsonDeserialize(as = QuestionImpl.class)
public interface Question extends Model {

	void setAlternatives(Collection<AlternativeImpl> alternatives);

	Collection<AlternativeImpl> getAlternatives();

	void setPunctuation(Integer punctuation);

	Integer getPunctuation();

	void setDescription(String description);

	String getDescription();

	void setChallenge(Challenge challenge);

	Challenge getChallenge();

}
