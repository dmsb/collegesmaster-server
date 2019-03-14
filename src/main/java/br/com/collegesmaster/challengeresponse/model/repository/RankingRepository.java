package br.com.collegesmaster.challengeresponse.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.challengeresponse.model.entity.Ranking;
import br.com.collegesmaster.challengeresponse.model.entity.impl.RankingImpl;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.security.model.entity.User;

@Repository
public interface RankingRepository extends JpaRepository<RankingImpl, Integer>, 
	QuerydslPredicateExecutor<RankingImpl> {
	
	List<Ranking> findTop5ByDisciplineOrderByScore(Discipline discipline);
	
	List<Ranking> findTop3ByScoreAndDisciplineOrderByScore(Integer Score, Discipline discipline);
	
	Ranking findByUserAndDiscipline(User user, Discipline discipline);	
}
