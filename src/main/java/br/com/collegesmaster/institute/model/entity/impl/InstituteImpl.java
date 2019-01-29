package br.com.collegesmaster.institute.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.institute.model.entity.Institute;

@Entity
@Table(name = "institute")
@Access(FIELD)
@Audited
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstituteImpl extends ModelImpl implements Institute {

    private static final long serialVersionUID = -7480055661943707725L;
	
    @NotNull
    @Column(name = "name",  nullable = false, length = 50)
    @Size(min = 3)
    private String name;
    
    @JsonIgnore
    @NotAudited
    @OneToMany(targetEntity = CourseImpl.class, cascade = ALL, 
    		fetch = LAZY, orphanRemoval = true, mappedBy = "institute")
    private Collection<CourseImpl> courses;
    
    @Column(name = "country", nullable = false, length = 50)
	private String country;
	
	@Column(name = "state", nullable = false, length = 30)
	private String state;
	
	@Column(name = "city", nullable = false, length = 50)
	private String city;
	
	public InstituteImpl() {
    	
	}
    
    public InstituteImpl(Integer id, String name, Long version) {
    	this.id = id;
    	this.name = name;
    	this.version = version;
    }
   	
	@Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name;
    }
    
    @Override
	public Collection<CourseImpl> getCourses() {
		return courses;
	}

	@Override
	public void setCourses(Collection<CourseImpl> courses) {
		this.courses = courses;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}
    
    @Override
    public boolean equals(final Object objectToBeComparated) {
    	
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof InstituteImpl)) {
			return false;
		}
		
		final InstituteImpl objectComparatedInstance = (InstituteImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
				Objects.equals(name, objectComparatedInstance.name);
	}
    
	@Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
