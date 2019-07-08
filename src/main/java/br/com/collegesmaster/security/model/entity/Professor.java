package br.com.collegesmaster.security.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.collegesmaster.security.model.entity.impl.ProfessorImpl;

@JsonDeserialize(as = ProfessorImpl.class)
public interface Professor extends User {

}
