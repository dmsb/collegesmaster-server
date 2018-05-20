package br.com.collegesmaster.security.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;

@Repository
public interface UserRepository extends JpaRepository<UserImpl, Integer>,
	QuerydslPredicateExecutor<User> {

	Boolean existsByCpf(String cpf);
	
	Boolean existsByEmail(String email);
	
	Boolean existsByUsername(String username);
	
	User findByUsername(String username);
	
}
