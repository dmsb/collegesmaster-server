package br.com.collegesmaster.security.model.entity.impl;

import static javax.persistence.AccessType.FIELD;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name="id")
@Access(FIELD)
@Audited
public class ProfessorImpl extends UserImpl {

	private static final long serialVersionUID = 2498444438852719879L;
	
}
