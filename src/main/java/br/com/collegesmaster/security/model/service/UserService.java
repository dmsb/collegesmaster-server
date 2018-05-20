package br.com.collegesmaster.security.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.collegesmaster.generics.GenericCRUD;
import br.com.collegesmaster.security.model.entity.User;

public interface UserService extends GenericCRUD<User>, UserDetailsService {

	User findById(final Integer id);
	
	Boolean existsByCpf(String cpf);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByUsername(String username);
	
	User findByUsername(String username);
}
