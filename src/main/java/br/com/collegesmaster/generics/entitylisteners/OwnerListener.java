package br.com.collegesmaster.generics.entitylisteners;

import javax.persistence.PrePersist;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.collegesmaster.generics.contracts.Owner;
import br.com.collegesmaster.security.model.entity.User;

public class OwnerListener {

    @PrePersist
    public <T extends User> void setRegisterOwner(Owner<T> registerOwner) {
    	
    	@SuppressWarnings("unchecked")
		T loggedUser = (T) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	registerOwner.setOwner(loggedUser);
    }
    
}
