package br.com.collegesmaster.challengeresponse.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challengeresponse.model.entity.impl.RankingImpl;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.security.model.entity.User;

@JsonDeserialize(as = RankingImpl.class)
public interface Ranking extends Model {

	void setUser(User user);

	User getUser();

	void setDiscipline(Discipline discipline);

	Discipline getDiscipline();

	void setPunctuation(Integer totalPunctuation);

	Integer getPunctuation();
	
}
