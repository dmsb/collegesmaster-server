package br.com.collegesmaster.institute.model.service;

import java.util.List;

import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Institute;

public interface CourseService extends GenericCRUD<Course> {
	
	List<Course> findByInstitute(final Institute institute);

}
