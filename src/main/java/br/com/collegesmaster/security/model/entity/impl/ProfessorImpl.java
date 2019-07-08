package br.com.collegesmaster.security.model.entity.impl;

import static javax.persistence.AccessType.FIELD;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.collegesmaster.security.model.entity.Professor;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name="id", foreignKey = @ForeignKey(name = "PROFESSOR_userFK"))
@Access(FIELD)
@Audited
public class ProfessorImpl extends UserImpl implements Professor {

	private static final long serialVersionUID = 2498444438852719879L;
	
}
