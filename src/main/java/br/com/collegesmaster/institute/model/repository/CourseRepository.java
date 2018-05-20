package br.com.collegesmaster.institute.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Institute;
import br.com.collegesmaster.institute.model.entity.impl.CourseImpl;

@Repository
public interface CourseRepository extends JpaRepository<CourseImpl, Integer>,
	QuerydslPredicateExecutor<CourseImpl> {

	List<Course> findByInstitute(final Institute institute);
	
}
