package br.com.collegesmaster.security.model.service;

import java.util.List;

import com.querydsl.core.types.Predicate;

import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.security.model.entity.Role;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;

public interface RoleService extends GenericCRUD<Role> {
	
	Role findById(final Integer id);

	List<Role> findElegiblesNames();
	
	Iterable<RoleImpl> findAll(Predicate predicate);
}
