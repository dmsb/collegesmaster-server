package br.com.collegesmaster.challenge.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.security.model.entity.User;

@Repository
public interface ChallengeRepository extends JpaRepository<ChallengeImpl, Integer>, 
	QuerydslPredicateExecutor<ChallengeImpl> {
	
	List<Challenge> findByUser(User user);
	
}
