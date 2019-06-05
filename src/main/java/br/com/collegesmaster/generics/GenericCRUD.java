package br.com.collegesmaster.generics;

import br.com.collegesmaster.generics.model.Model;

public interface GenericCRUD <T extends Model> {
	
	T create(T model);
	
	T update(T model);
	
	void deleteById(Integer id);
	
	T findById(Integer id);
	
}
