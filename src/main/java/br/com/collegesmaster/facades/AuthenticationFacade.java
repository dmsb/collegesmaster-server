package br.com.collegesmaster.facades;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {	
	Authentication getAuthentication();
}
