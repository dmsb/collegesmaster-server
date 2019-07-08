package br.com.collegesmaster.institute.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Institute;
import br.com.collegesmaster.institute.model.entity.impl.CourseImpl;
import br.com.collegesmaster.institute.model.repository.CourseRepository;
import br.com.collegesmaster.institute.model.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;	
	
	@PreAuthorize("hasAuthority('CREATE_COURSE')")
	@Transactional
	@Override
	public Course create(Course course) {
		return courseRepository.save((CourseImpl)course);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_COURSE')")
	@Transactional
	@Override
	public Course update(Course course) {
		return courseRepository.save((CourseImpl)course);
	}

	@PreAuthorize("hasAuthority('DELETE_COURSE')")
	@Transactional
	@Override
	public void deleteById(final Integer id) {
		courseRepository.deleteById(id);
	}

	@PreAuthorize("hasAuthority('READ_COURSE')")
	@Transactional
	@Override
	public Course findById(Integer id) {
		return courseRepository.findById(id).orElse(null);
	}
	
	@PreAuthorize("hasAuthority('READ_COURSE')")
	@Transactional
	@Override
	public List<Course> findByInstitute(final Institute institute) {
		return courseRepository.findByInstitute(institute);
	}	
}
