package br.com.collegesmaster.facades.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.collegesmaster.facades.AuthenticationFacade;

@Component
public class AuthenticationImpl implements AuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
