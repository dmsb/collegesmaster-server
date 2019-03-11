package br.com.collegesmaster.challenge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;

@Repository
public interface AlternativeRepository extends JpaRepository<AlternativeImpl, Integer>, 
	QuerydslPredicateExecutor<AlternativeImpl> {

}
