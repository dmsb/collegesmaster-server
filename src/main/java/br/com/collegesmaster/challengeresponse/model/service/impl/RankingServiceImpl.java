package br.com.collegesmaster.challengeresponse.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.challengeresponse.model.entity.Ranking;
import br.com.collegesmaster.challengeresponse.model.entity.impl.RankingImpl;
import br.com.collegesmaster.challengeresponse.model.repository.RankingRepository;
import br.com.collegesmaster.challengeresponse.model.service.RankingService;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.security.model.entity.User;

@Service("rankingService")
public class RankingServiceImpl implements RankingService {
	
	@Autowired
	private RankingRepository rankingRepository;
	
	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public Ranking create(final Ranking ranking) {
		return rankingRepository.save((RankingImpl)ranking);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public Ranking update(final Ranking ranking) {
		return rankingRepository.save((RankingImpl)ranking);
	}
	
	@Transactional
	@Override
	public void deleteById(final Integer id) {
		rankingRepository.deleteById(id);
	}
	
	@Transactional
	@Override
	public List<Ranking> findTop5DisciplineOrderByPontuation(final Discipline discipline) {
			return rankingRepository.findTop5ByDisciplineOrderByScore(discipline);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void addScoreToUser(final ChallengeResponse challengeResponse) {
		if(challengeResponse != null && challengeResponse.getUser() != null) {
			final Ranking userRankingInPeriod = this.findByUserAndDiscipline(
					challengeResponse.getUser(),
					challengeResponse.getChallenge().getDiscipline());
			if(userRankingInPeriod != null) {
				final Integer currentPontuation = addStudentPontuation(challengeResponse.getScore(), 
						userRankingInPeriod.getScore());
				userRankingInPeriod.setScore(currentPontuation);
				this.update(userRankingInPeriod);
			} else {
				final RankingImpl newUserRanking = buildANewRanking(challengeResponse);
				this.create(newUserRanking);
			}
		}
	}

	private Integer addStudentPontuation(final Integer currentChallengeResponseScore,
			final Integer currentScore) {
		return currentScore + currentChallengeResponseScore;
	}

	private RankingImpl buildANewRanking(final ChallengeResponse challengeResponse) {
		final RankingImpl newUserRanking = new RankingImpl();
		newUserRanking.setDiscipline(challengeResponse.getChallenge().getDiscipline());
		newUserRanking.setScore(challengeResponse.getScore());
		newUserRanking.setUser(challengeResponse.getUser());
		return newUserRanking;
	}
	
	@Transactional
	@Override
	public Ranking findByUserAndDiscipline(final User user, final Discipline discipline) {
		return rankingRepository.findByUserAndDiscipline(user, discipline);
	}

	@Transactional
	@Override
	public Ranking findById(Integer id) {
		return rankingRepository.findById(id).orElse(null);
	}
}
