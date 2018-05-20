package br.com.collegesmaster.generics.auditinfo.impl;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import br.com.collegesmaster.generics.auditinfo.AuditInfo;


@Entity
@Table(name = "audit_info")
@RevisionEntity(AuditListener.class)
public class AuditInfoImpl extends DefaultRevisionEntity implements AuditInfo {

	private static final long serialVersionUID = 8483893854959148773L;
	
	private String username;

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}
}
