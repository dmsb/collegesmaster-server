package br.com.collegesmaster.institute.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.institute.model.entity.impl.QDisciplineImpl;

@Repository
public interface DisciplineRepository extends JpaRepository<DisciplineImpl, Integer>,
	QuerydslPredicateExecutor<DisciplineImpl>, QuerydslBinderCustomizer<QDisciplineImpl> {
	
	@Override
	default void customize(QuerydslBindings bindings, QDisciplineImpl root) {
		bindings.bind(root.name).first((path, value) -> path.contains(value));
	}
	List<Discipline> findByCourse(final Course course);
}
