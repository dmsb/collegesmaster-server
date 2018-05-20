package br.com.collegesmaster.challenge.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionImpl, Integer>,
	QuerydslPredicateExecutor<QuestionImpl> {
	
	List<Question> findByChallenge(Challenge challenge);
}
