package br.com.collegesmaster.challenge.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.impl.BadgeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.security.model.entity.Professor;
import br.com.collegesmaster.security.model.entity.impl.StudentImpl;

@JsonDeserialize(as = BadgeImpl.class)
public interface Badge extends Model {

	void setProfessor(Professor professor);

	Professor getProfessor();

	void setStudents(Collection<StudentImpl> students);

	Collection<StudentImpl> getStudents();

	void setChallenges(Collection<ChallengeImpl> challenges);

	Collection<ChallengeImpl> getChallenges();

	void setName(String name);

	String getName();

}
