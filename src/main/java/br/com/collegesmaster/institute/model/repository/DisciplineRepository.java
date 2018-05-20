package br.com.collegesmaster.institute.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineImpl, Integer>,
	QuerydslPredicateExecutor<DisciplineImpl> {

	List<Discipline> findByCourse(final Course course);
}
