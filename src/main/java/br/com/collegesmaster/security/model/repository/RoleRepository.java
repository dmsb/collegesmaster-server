package br.com.collegesmaster.security.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.collegesmaster.security.model.entity.Role;
import br.com.collegesmaster.security.model.entity.impl.RoleImpl;

@Repository
public interface RoleRepository extends JpaRepository<RoleImpl, Integer>,
	QuerydslPredicateExecutor<RoleImpl>{
	
	List<Role> findByNameNot(String name);
}
