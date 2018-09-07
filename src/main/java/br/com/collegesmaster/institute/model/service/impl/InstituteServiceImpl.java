package br.com.collegesmaster.institute.model.service.impl;

import static java.lang.Boolean.TRUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.institute.model.entity.Institute;
import br.com.collegesmaster.institute.model.entity.impl.InstituteImpl;
import br.com.collegesmaster.institute.model.repository.InstituteRepository;
import br.com.collegesmaster.institute.model.service.InstituteService;

@Service("instituteService")
public class InstituteServiceImpl implements InstituteService {
	
	@Autowired
	private InstituteRepository instituteRepository;
	
	@Transactional
	@Override
	public Institute create(final Institute institute) {
		return instituteRepository.save((InstituteImpl)institute);
	}
	
	@Transactional
	@Override
	public Institute update(final Institute institute) {
		return instituteRepository.save((InstituteImpl)institute);
	}
	
	@Transactional
	@Override
	public Boolean remove(final Institute institute) {
		instituteRepository.delete((InstituteImpl)institute);
		return TRUE;
	}
	
	@Transactional
	@Override
	public Institute findById(final Integer id) {
		return instituteRepository.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public Iterable<InstituteImpl> findAll() {
		return instituteRepository.findAll();
	}
}