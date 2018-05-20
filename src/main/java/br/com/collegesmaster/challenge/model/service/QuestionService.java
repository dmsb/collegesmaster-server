package br.com.collegesmaster.challenge.model.service;

import java.util.List;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.Question;

public interface QuestionService {

	List<Question> findByChallenge(Challenge challenge);
}
