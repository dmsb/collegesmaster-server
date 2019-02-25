package br.com.collegesmaster.institute.model.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;

public interface DisciplineService extends GenericCRUD<Discipline> {

	List<Discipline> findByCourse(final Course course);

	Iterable<DisciplineImpl> findByPredicate(final Predicate predicate, final Sort sort);
	
}
