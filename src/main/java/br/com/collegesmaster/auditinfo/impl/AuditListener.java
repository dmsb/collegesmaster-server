package br.com.collegesmaster.auditinfo.impl;

import org.hibernate.envers.RevisionListener;
import org.jboss.logging.Logger;

import br.com.collegesmaster.auditinfo.AuditInfo;

public class AuditListener implements RevisionListener {
	
	protected final static Logger LOGGER = Logger.getLogger(AuditListener.class);
	
	@Override
	public void newRevision(final Object revisionEntity) {

		final AuditInfo auditInfo = (AuditInfo) revisionEntity;
		auditInfo.setUsername("Anonymous");
	}
}
