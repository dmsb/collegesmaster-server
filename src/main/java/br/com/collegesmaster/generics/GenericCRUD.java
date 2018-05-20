package br.com.collegesmaster.generics;

import br.com.collegesmaster.generics.model.Model;

public interface GenericCRUD <T extends Model> {
	
	T create(T model);
	
	T update(T model);

	Boolean remove(T model);
	
	T findById(Integer id);
	
}
