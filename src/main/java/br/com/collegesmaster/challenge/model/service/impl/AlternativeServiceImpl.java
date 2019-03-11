package br.com.collegesmaster.challenge.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challenge.model.repository.AlternativeRepository;
import br.com.collegesmaster.challenge.model.service.AlternativeService;

@Service
public class AlternativeServiceImpl implements AlternativeService {

	@Autowired
	private AlternativeRepository alternativeRespository;
	
	@Override
	public Iterable<AlternativeImpl> findAlternatives(final Predicate predicate) {
		return alternativeRespository.findAll(predicate);
	}

	
	
}
