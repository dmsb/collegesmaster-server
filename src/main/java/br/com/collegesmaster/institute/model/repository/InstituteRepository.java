package br.com.collegesmaster.institute.model.repository;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.LOAD;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;

@Repository
public interface InstituteRepository extends JpaRepository<InstituteImpl, Integer>,
	QuerydslPredicateExecutor<InstituteImpl>{
	
	@EntityGraph(type = LOAD, attributePaths = { "courses" })
	@Override
	List<InstituteImpl> findAll();
	
}
