package br.com.collegesmaster.challengeresponse.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.challengeresponse.model.entity.impl.ChallengeResponseImpl;
import br.com.collegesmaster.security.model.entity.User;

@Repository
public interface ChallengeResponseRepository extends JpaRepository<ChallengeResponseImpl, Integer>, 
QuerydslPredicateExecutor<ChallengeResponseImpl> {

	@Query("SELECT u FROM ChallengeResponseImpl cr JOIN ChallengeResponseImpl u WHERE cr.user = ?1")
	User repliedByUser(User user);
	
	List<ChallengeResponse> findByUser(User user);
	
	List<ChallengeResponse> findByChallenge(Challenge selectedChallenge);
}
