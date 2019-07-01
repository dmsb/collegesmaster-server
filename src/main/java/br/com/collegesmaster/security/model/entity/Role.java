package br.com.collegesmaster.security.model.entity;

import java.util.Collection;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.security.model.entity.impl.PrivilegeImpl;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;

@JsonDeserialize(as = RoleImpl.class)
public interface Role extends Model {

	void setName(String name);

	String getName();

	void setPrivileges(Collection<PrivilegeImpl> privileges);

	Collection<PrivilegeImpl> getPrivileges();

	void setUsers(Collection<UserImpl> users);

	Collection<UserImpl> getUsers();

}
