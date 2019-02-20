package br.com.collegesmaster.challenge.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.enums.ChallengeType;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.security.model.entity.User;

@JsonDeserialize(as = ChallengeImpl.class)
public interface Challenge extends Model {

	User getUser();

	void setUser(User user);

	Discipline getDiscipline();

	void setDiscipline(Discipline discipline);

	void setQuestions(Collection<QuestionImpl> questions);

	Collection<QuestionImpl> getQuestions();

	void setTitle(String title);

	String getTitle();

	void setChallengeType(ChallengeType challengetType);

	ChallengeType getChallengeType();

	void setEnabled(Boolean enabled);

	Boolean getEnabled();
	
}