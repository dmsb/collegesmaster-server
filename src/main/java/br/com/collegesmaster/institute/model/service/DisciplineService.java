package br.com.collegesmaster.institute.model.service;

import java.util.List;

import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;

public interface DisciplineService extends GenericCRUD<Discipline> {

	List<Discipline> findByCourse(final Course course);
	
}
