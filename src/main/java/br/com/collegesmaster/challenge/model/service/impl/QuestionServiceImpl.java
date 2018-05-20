package br.com.collegesmaster.challenge.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.repository.QuestionRepository;
import br.com.collegesmaster.challenge.model.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public List<Question> findByChallenge(Challenge challenge) {
		return questionRepository.findByChallenge(challenge);
	}

}
