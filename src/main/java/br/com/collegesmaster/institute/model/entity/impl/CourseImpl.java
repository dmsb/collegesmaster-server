package br.com.collegesmaster.institute.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Institute;

@Entity
@Table(name = "course")
@Access(FIELD)
@Audited
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseImpl extends ModelImpl implements Course {

	private static final long serialVersionUID = -8528499270451458997L;
	
	@NotNull
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@NotNull
	@ManyToOne(targetEntity = InstituteImpl.class, optional = false, fetch = EAGER)
	@JoinColumn(name = "instituteFK", referencedColumnName = "id", updatable = false,
		foreignKey = @ForeignKey(name = "COURSE_instituteFK"))
	private Institute institute;
	
	@JsonIgnore
	@NotNull
	@OneToMany(targetEntity = DisciplineImpl.class, cascade = ALL, fetch = LAZY,
			orphanRemoval = true, mappedBy = "course")
	private Collection<DisciplineImpl> disciplines;
	
	public CourseImpl() {
    	
	}
    
    public CourseImpl(Integer id, String name, Long version) {
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
	public Institute getInstitute() {
		return institute;
	}
	
	@Override
	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	@Override
	public Collection<DisciplineImpl> getDisciplines() {
		return disciplines;
	}

	@Override
	public void setDisciplines(Collection<DisciplineImpl> disciplines) {
		this.disciplines = disciplines;
	}
	
	@Override
	public boolean equals(final Object objectToBeComparated) {
		
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof CourseImpl)) {
			return false;
		}
		
		final CourseImpl objectComparatedInstance = (CourseImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
			    Objects.equals(name, objectComparatedInstance.name);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
