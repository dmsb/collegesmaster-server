package br.com.collegesmaster.institute.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.institute.model.repository.DisciplineRepository;
import br.com.collegesmaster.institute.model.service.DisciplineService;

@Service("disciplineService")
public class DisciplineServiceImpl implements DisciplineService {
	
	@Autowired
	private DisciplineRepository disciplineRepository;
	
	@Transactional
	@Override
	public Discipline create(Discipline discipline) {
		return disciplineRepository.save((DisciplineImpl)discipline);
	}

	@Transactional
	@Override
	public Discipline update(Discipline discipline) {
		return disciplineRepository.save((DisciplineImpl)discipline);
	}

	@Transactional
	@Override
	public Boolean remove(Discipline discipline) {
		disciplineRepository.delete((DisciplineImpl)discipline);
		return Boolean.TRUE;
	}
	
	@Transactional
	@Override
	public Discipline findById(Integer id) {
		return disciplineRepository.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public List<Discipline> findByCourse(final Course course) {
		return disciplineRepository.findByCourse(course);
	}
}
