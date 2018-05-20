package br.com.collegesmaster.challenge.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.enums.Letter;
import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.generics.model.Model;

@JsonDeserialize(as = AlternativeImpl.class)
public interface Alternative extends Model {

	void setDescription(String description);

	String getDescription();

	void setLetter(Letter letter);

	Letter getLetter();

	void setIsTrue(Boolean isTrue);
	
	Boolean getIsTrue();

	void setQuestion(Question question);

	Question getQuestion();

}
