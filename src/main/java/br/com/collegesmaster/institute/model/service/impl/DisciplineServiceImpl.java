package br.com.collegesmaster.institute.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.generics.facade.AuthenticationFacade;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.institute.model.entity.impl.QDisciplineImpl;
import br.com.collegesmaster.institute.model.repository.DisciplineRepository;
import br.com.collegesmaster.institute.model.service.DisciplineService;

@Service("disciplineService")
public class DisciplineServiceImpl implements DisciplineService {
	
	@Autowired
	private AuthenticationFacade authenticationFacade;
	
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
	public Boolean deleteById(final Integer id) {
		disciplineRepository.deleteById(id);
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
	
	@Override
	@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'PROFESSOR' )")
	@Transactional(readOnly = true)
	public Iterable<DisciplineImpl> findByPredicate(final Predicate predicate, final Sort sort) {
		final String loggedUsername = authenticationFacade.getAuthentication().getName();
		final BooleanBuilder booleanBuilderQuery = new BooleanBuilder(predicate);
		booleanBuilderQuery.and(QDisciplineImpl.disciplineImpl.relatedUsersInSemeter.any().username.eq(loggedUsername));
		return this.disciplineRepository.findAll(booleanBuilderQuery.getValue(), sort);
	}
}
