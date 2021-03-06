package br.com.collegesmaster.institute.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.security.model.entity.impl.ProfessorImpl;
import br.com.collegesmaster.security.model.entity.impl.StudentImpl;

@JsonDeserialize(as = DisciplineImpl.class)
public interface Discipline extends Model {

	Course getCourse();

	void setCourse(Course course);

	String getName();

	void setName(String name);

	void setStudents(Collection<StudentImpl> students);

	Collection<StudentImpl> getStudents();

	void setProfessors(Collection<ProfessorImpl> professor);

	Collection<ProfessorImpl> getProfessors();

}