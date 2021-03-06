package br.com.collegesmaster.security.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.challenge.model.entity.impl.BadgeImpl;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.security.model.entity.enums.StudentLevelDesignation;
import br.com.collegesmaster.security.model.entity.impl.StudentImpl;

@JsonDeserialize(as = StudentImpl.class)
public interface Student extends User {

	void setCourse(Course course);

	Course getCourse();

	void setLevelDesignation(StudentLevelDesignation levelDesignation);

	StudentLevelDesignation getLevelDesignation();

	void setLevel(Integer level);

	Integer getLevel();

	void setBadges(Collection<BadgeImpl> badges);

	Collection<BadgeImpl> getBadges();

}
