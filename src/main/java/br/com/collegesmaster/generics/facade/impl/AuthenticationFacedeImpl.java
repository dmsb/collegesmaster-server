package br.com.collegesmaster.generics.facade.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.collegesmaster.generics.facade.AuthenticationFacade;

@Component
public class AuthenticationFacedeImpl implements AuthenticationFacade {

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
