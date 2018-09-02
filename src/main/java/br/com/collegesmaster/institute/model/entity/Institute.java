package br.com.collegesmaster.institute.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.generics.localization.Localization;
import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.institute.model.entity.impl.CourseImpl;
import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;

@JsonDeserialize(as = InstituteImpl.class)
public interface Institute extends Localization, Model {

	String getName();

	void setName(String name);

	Collection<CourseImpl> getCourses();

	void setCourses(Collection<CourseImpl> courses);

}