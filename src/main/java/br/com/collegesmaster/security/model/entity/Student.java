package br.com.collegesmaster.security.model.entity;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.security.model.entity.enums.StudentLevelDesignation;

public interface Student {

	void setCourse(Course course);

	Course getCourse();

	void setLevelDesignation(StudentLevelDesignation levelDesignation);

	StudentLevelDesignation getLevelDesignation();

	void setLevel(Integer level);

	Integer getLevel();

}
