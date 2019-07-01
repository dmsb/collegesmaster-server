package br.com.collegesmaster.security.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.security.model.entity.Privilege;
import br.com.collegesmaster.security.model.entity.Role;
import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.service.impl.Password;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "user",
	uniqueConstraints = {
			@UniqueConstraint(columnNames = "username",  name = "UK_USER_username"),
			@UniqueConstraint(columnNames = "cpf",  name = "UK_GI_username"),
			@UniqueConstraint(columnNames = "email",  name = "UK_GI_email")})
@Access(FIELD)
@Audited
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserImpl extends ModelImpl implements User {

    private static final long serialVersionUID = -7809703915845045860L;
	
	@NotNull
    @Size(min = 2, max = 25)
    @Column(name = "username", length = 25, nullable = false)
    private String username;
    
	@NotNull
    @Password
    @Basic(fetch = LAZY)
	@Column(name = "password", nullable = false, length = 88)
    private String password;
	
	@NotNull
	@CPF
	@Column(name = "cpf", nullable = false, length = 11)	
    private String cpf;
    
	@Email
	@Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull
	@Size(max = 25)
	@Column(name = "firstName", nullable = false, length = 25)
    private String firstName;

    @NotNull
	@Size(max = 80)
    @Column(name = "lastName", nullable = false, length = 80)
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;
    
    @JsonBackReference
    @NotEmpty
    @ManyToMany(fetch = EAGER)
    @JoinTable(name="user_has_roles",
	    joinColumns = {@JoinColumn(name="userFK", referencedColumnName = "id")},
	    foreignKey = @ForeignKey(name = "USER_HAS_ROLES_userFK"),
	    inverseJoinColumns = {@JoinColumn(name="roleFK", referencedColumnName = "id")},
	    inverseForeignKey = @ForeignKey(name = "USER_HAS_ROLES_roleFK"))
    private Collection<RoleImpl> roles;
    
    @PrePersist
    @PreUpdate
    @Override
	public void parseCpfToCrude() {
    	if(this.getCpf() != null) {
    		final String crudeCpf = this.getCpf().replaceAll("[^0-9]", "");
    		setCpf(crudeCpf);    		
    	}
    }
	
    @Override
	public Boolean isUserInRole(final String roleName) {
    	final List<String> userRoleNames = getRoleNames();
    	return userRoleNames.stream().anyMatch(roleName::equals);
    }
    
    @Override
	public Boolean isUserInAnyRoles(final List<String> roleNames) {
    	final List<String> userRoleNames = getRoleNames();
    	return roleNames.stream().anyMatch(userRoleNames::contains);
    }

	@Override
	public List<String> getRoleNames() {
		return getRoles().stream().map(role -> {return role.getName();})
    		.collect(Collectors.toList());
	}
	
	@Override
	public String getCpf() {
        return cpf;
    }

	@Override
	public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public LocalDate getBirthdate() {
		return birthdate;
	}

	@Override
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
    
    @Override
	public String getUsername() {
  		return username;
  	}
    
  	@Override
	public void setUsername(String username) {
  		this.username = username;
  	}
  	
    @Override
	public String getPassword() {
        return password;
    }

    @Override
	public void setPassword(String password) {
        this.password = password;
    }
    
	@Override
	public Collection<RoleImpl> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(Collection<RoleImpl> roles) {
		this.roles = roles;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(final Role currentRole : this.getRoles()) {
			for(final Privilege currentPrivilege : currentRole.getPrivileges()) {
				authorities.add(new SimpleGrantedAuthority(currentPrivilege.getName()));
			}
		}
//        for (final String role : this.getRoleNames()) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
        return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(final Object objectToBeComparated) {

		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof UserImpl)) {
			return false;
		}
		
		final UserImpl objectComparatedInstance = (UserImpl) objectToBeComparated;
		
		return Objects.equals(id, objectComparatedInstance.id) &&
				Objects.equals(this.username, objectComparatedInstance.username);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

}
