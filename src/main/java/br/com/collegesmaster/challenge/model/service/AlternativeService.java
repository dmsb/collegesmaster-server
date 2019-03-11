package br.com.collegesmaster.challenge.model.service;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;

public interface AlternativeService {

	Iterable<AlternativeImpl> findAlternatives(final Predicate predicate);
	
}
