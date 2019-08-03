package br.com.collegesmaster.challengeresponse.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.challengeresponse.model.entity.AnswerBook;
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
	public void addScoreToUser(final AnswerBook answerBook) {
		if(answerBook != null && answerBook.getOwner() != null) {
			final Ranking userRankingInPeriod = this.findByUserAndDiscipline(
					answerBook.getOwner(),
					answerBook.getChallenge().getDiscipline());
			if(userRankingInPeriod != null) {
				final Integer currentPontuation = addStudentPontuation(answerBook.getScore(), 
						userRankingInPeriod.getScore());
				userRankingInPeriod.setScore(currentPontuation);
				this.update(userRankingInPeriod);
			} else {
				final RankingImpl newUserRanking = buildANewRanking(answerBook);
				this.create(newUserRanking);
			}
		}
	}

	private Integer addStudentPontuation(final Integer currentChallengeResponseScore,
			final Integer currentScore) {
		return currentScore + currentChallengeResponseScore;
	}

	private RankingImpl buildANewRanking(final AnswerBook answerBook) {
		final RankingImpl newUserRanking = new RankingImpl();
		newUserRanking.setDiscipline(answerBook.getChallenge().getDiscipline());
		newUserRanking.setScore(answerBook.getScore());
		newUserRanking.setUser(answerBook.getOwner());
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
