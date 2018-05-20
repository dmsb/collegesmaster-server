package br.com.collegesmaster.institute.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.institute.model.entity.impl.CourseImpl;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;

@JsonDeserialize(as = CourseImpl.class)
public interface Course extends Model{

	String getName();

	void setName(String name);

	Institute getInstitute();

	void setInstitute(Institute institute);

	Collection<DisciplineImpl> getDisciplines();

	void setDisciplines(Collection<DisciplineImpl> disciplines);

}