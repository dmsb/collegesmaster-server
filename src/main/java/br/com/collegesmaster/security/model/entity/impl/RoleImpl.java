	package br.com.collegesmaster.security.model.entity.impl;

import static javax.persistence.AccessType.FIELD;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.security.model.entity.Role;

@Entity
@Table(name = "role")
@Access(FIELD)
@Audited
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleImpl extends ModelImpl implements Role {

	private static final long serialVersionUID = -8835309684958820875L;
	
	@NotNull
	@Column(name = "name", nullable = false, unique = true, length = 20)
	private String name;
	
	@JsonManagedReference
	@ManyToMany(mappedBy = "roles")
    private Collection<UserImpl> users;
	
	@JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_has_privileges", 
		joinColumns = {@JoinColumn(name="roleFK", referencedColumnName = "id")},
		foreignKey = @ForeignKey(name = "ROLE_HAS_PRIVILEGES_roleFK"),
	    inverseJoinColumns = {@JoinColumn(name="privilegeFK", referencedColumnName = "id")},
	    inverseForeignKey = @ForeignKey(name = "ROLE_HAS_PRIVILEGES_privilegeFK"))
    private Collection<PrivilegeImpl> privileges; 
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Collection<UserImpl> getUsers() {
		return users;
	}

	@Override
	public void setUsers(Collection<UserImpl> users) {
		this.users = users;
	}

	@Override
	public Collection<PrivilegeImpl> getPrivileges() {
		return privileges;
	}

	@Override
	public void setPrivileges(Collection<PrivilegeImpl> privileges) {
		this.privileges = privileges;
	}

	@Override
	public boolean equals(final Object objectToBeComparated) {
		
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof RoleImpl)) {
			return false;
		}
		
		final RoleImpl objectComparatedInstance = (RoleImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) &&
				Objects.equals(this.name, objectComparatedInstance.name);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }
}
