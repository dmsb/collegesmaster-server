package br.com.collegesmaster.institute.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;

@JsonDeserialize(as = DisciplineImpl.class)
public interface Discipline extends Model {

	Course getCourse();

	void setCourse(Course course);

	String getName();

	void setName(String name);

	void setChallenges(Collection<ChallengeImpl> challenges);

	Collection<ChallengeImpl> getChallenges();

	void setRelatedUsersInSemeter(Collection<UserImpl> relatedUsersInSemeter);

	Collection<UserImpl> getRelatedUsersInSemeter();

}