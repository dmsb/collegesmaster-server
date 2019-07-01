package br.com.collegesmaster.security.model.entity;

import java.util.Collection;

import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;

public interface Privilege extends Model {
	
    Collection<RoleImpl> getRoles();
    
    void setRoles(Collection<RoleImpl> roles);

	void setName(String name);

	String getName();
}
