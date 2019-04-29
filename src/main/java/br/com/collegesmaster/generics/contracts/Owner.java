package br.com.collegesmaster.generics.contracts;

import br.com.collegesmaster.security.model.entity.User;

public interface Owner {

	User getOwner();
	
	void setOwner(User owner);
}
