package br.com.collegesmaster.challengeresponse.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challengeresponse.model.entity.AnswerBook;
import br.com.collegesmaster.challengeresponse.model.entity.impl.AnswerBookImpl;
import br.com.collegesmaster.challengeresponse.model.entity.impl.QAnswerBookImpl;
import br.com.collegesmaster.challengeresponse.model.repository.AnswerBookRepository;
import br.com.collegesmaster.challengeresponse.model.service.AnswerBookService;
import br.com.collegesmaster.challengeresponse.model.service.RankingService;
import br.com.collegesmaster.generics.facade.AuthenticationFacade;
import br.com.collegesmaster.security.model.entity.User;

@Service("answerBookService")
public class AnswerBookServiceImpl implements AnswerBookService {
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
	@Autowired
	private AnswerBookRepository answerBookRepository;
	
	@Autowired
	private RankingService rankingBusiness;
	
	@PreAuthorize("hasAnyAuthority('CREATE_ANSWER_BOOK')")
	@Transactional
	@Override
	public AnswerBook create(final AnswerBook response) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
		final List<AnswerBook> loggedUseraAnsweredBooksOfCurrentChallenge = answerBookRepository.findByChallengeAndOwner(response.getChallenge(), user);
		if(loggedUseraAnsweredBooksOfCurrentChallenge.isEmpty()) {
			final AnswerBook createdResponse = answerBookRepository.save((AnswerBookImpl)response);
			if(createdResponse != null) {
				rankingBusiness.addScoreToUser(response);
			}
			return createdResponse;
		} 
		return null;
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ANSWER_BOOK')")
	@Transactional
	@Override
	public AnswerBook update(final AnswerBook response) {
		return answerBookRepository.save((AnswerBookImpl)response);
	}
	
	@PreAuthorize("hasAuthority('DELETE_ANSWER_BOOK')")
	@Transactional
	@Override
	public void deleteById(final Integer id) {
		answerBookRepository.deleteById(id);
	}
	
	@PreAuthorize("hasAuthority('READ_ANSWER_BOOK')")
	@Transactional
	@Override
	public AnswerBook findById(final Integer id) {
		return answerBookRepository
					.findById(id)
					.orElse(null);
	}
	
	@PreAuthorize("hasAuthority('READ_ANSWER_BOOK')")
	@Transactional
	@Override
	public List<AnswerBook> findByUser(final User user) {
		return answerBookRepository.findByOwner(user);
		
	}
	
	@PreAuthorize("hasAuthority('READ_ANSWER_BOOK')")
	@Transactional
	@Override
	public List<AnswerBook> findByChallenge(Challenge selectedChallenge) {
		return answerBookRepository.findByChallenge(selectedChallenge);
	}
	
	@PreAuthorize("hasAuthority('READ_ANSWER_BOOK')")
	@Transactional(readOnly = true)
	@Override
	public Page<AnswerBookImpl> findByPredicate(final Predicate predicate, final Pageable pageable) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		
		booleanBuilderQuery.and(QAnswerBookImpl.answerBookImpl.owner.username.eq(loggedUsername));
		return this.answerBookRepository.findAll(booleanBuilderQuery.getValue(), pageable);
	}

}
