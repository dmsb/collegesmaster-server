package br.com.collegesmaster.institute.model.service;

import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.institute.model.entity.Institute;
import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;

public interface InstituteService extends GenericCRUD<Institute> {
	
	Iterable<InstituteImpl> findAll();
}
