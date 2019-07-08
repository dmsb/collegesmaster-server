package br.com.collegesmaster.security.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.security.model.entity.Privilege;

@Entity
@Table(name = "privilege")
@Access(FIELD)
@Audited
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivilegeImpl extends ModelImpl implements Privilege {

	private static final long serialVersionUID = 4492027072723965090L;
	
	@NotNull
	@Column(name = "name", nullable = false, unique = true, length = 50)
	private String name;
	
    @ManyToMany(mappedBy = "privileges", fetch = LAZY)
    private Collection<RoleImpl> roles;
    
	@Override
	public Collection<RoleImpl> getRoles() {
		return null;
	}

	@Override
	public void setRoles(Collection<RoleImpl> roles) {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
