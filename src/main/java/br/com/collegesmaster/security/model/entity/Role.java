package br.com.collegesmaster.security.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.generics.model.Model;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;

@JsonDeserialize(as = RoleImpl.class)
public interface Role extends Model {

	void setName(String name);

	String getName();

}
