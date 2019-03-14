package br.com.collegesmaster.challengeresponse.model.service;

import java.util.List;

import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.challengeresponse.model.entity.Ranking;
import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.security.model.entity.User;

public interface RankingService extends GenericCRUD<Ranking> {
	
	void addScoreToUser(ChallengeResponse challengeResponse);
	
	Ranking findByUserAndDiscipline(User user, Discipline discipline);

	List<Ranking> findTop5DisciplineOrderByPontuation(Discipline discipline);
}
