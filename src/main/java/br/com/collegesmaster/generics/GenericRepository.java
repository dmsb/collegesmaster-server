package br.com.collegesmaster.generics;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

import com.querydsl.core.types.EntityPath;

public interface GenericRepository <T, ID extends Serializable> 
	extends JpaRepository<T, ID>, QuerydslBinderCustomizer<EntityPath<?>> {
	
	public List<T> findByAttributeContainsText(String attributeName, String text);
	
}
