package br.com.collegesmaster.generics.contracts;

import br.com.collegesmaster.security.model.entity.User;

public interface Owner <T extends User> {

	T getOwner();
	
	void setOwner(T owner);
}
