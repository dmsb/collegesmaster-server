package br.com.collegesmaster.security.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.security.model.entity.Role;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;
import br.com.collegesmaster.security.model.repository.RoleRepository;
import br.com.collegesmaster.security.model.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@Transactional
	@Override
	public Role create(Role role) {
		return roleRepository.save((RoleImpl)role);
	}

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@Transactional
	@Override
	public Role update(Role role) {
		return roleRepository.save((RoleImpl)role);
	}

	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	@Transactional
	@Override
	public Boolean remove(Role role) {
		roleRepository.delete((RoleImpl)role);
		return true;
	}
	
	@Transactional
	@Override
	public Role findById(Integer id) {
		return roleRepository.findById(id).orElse(null);
	}
	
	@Transactional	
	@Override
	public List<Role> findElegiblesNames() {
		return roleRepository.findByNameNot("ADMINISTRATOR");
	}
	
	@Transactional
	@Override
	public Iterable<Role> findAll(final Predicate predicate) {	
		return roleRepository.findAll(predicate);
	}
}