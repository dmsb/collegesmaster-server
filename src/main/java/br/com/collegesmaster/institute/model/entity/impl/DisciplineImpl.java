package br.com.collegesmaster.institute.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.security.model.entity.impl.ProfessorImpl;
import br.com.collegesmaster.security.model.entity.impl.StudentImpl;

@Entity
@Table(name = "discipline")
@Access(FIELD)
@Audited
public class DisciplineImpl extends ModelImpl implements Discipline {

    private static final long serialVersionUID = -8467860341227715787L;
	
	@NotNull
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @NotNull
    @ManyToOne(targetEntity = CourseImpl.class, optional = false, fetch = EAGER)
    @JoinColumn(name = "courseFK", referencedColumnName = "id",
    	foreignKey = @ForeignKey(name = "DISCIPLINE_courseFK"))
    private Course course;
	
    @ManyToMany(fetch = LAZY)
    @JoinTable(name="discipline_has_students",
	    joinColumns = {@JoinColumn(name="disciplineFK", referencedColumnName = "id")},
	    foreignKey = @ForeignKey(name = "DISCIPLINE_HAS_STUDENTS_disciplineFK"),
	    inverseJoinColumns = {@JoinColumn(name="studentFK", referencedColumnName = "id")},
	    inverseForeignKey = @ForeignKey(name = "DISCIPLINE_HAS_STUDENTS_studentFK"))
    private Collection<StudentImpl> students;
    
    @ManyToMany(fetch = LAZY)
    @JoinTable(name="discipline_has_professors",
	    joinColumns = {@JoinColumn(name="disciplineFK", referencedColumnName = "id")},
	    foreignKey = @ForeignKey(name = "DISCIPLINE_HAS_PROFESSORS_disciplineFK"),
	    inverseJoinColumns = {@JoinColumn(name="professorFK", referencedColumnName = "id")},
	    inverseForeignKey = @ForeignKey(name = "DISCIPLINE_HAS_PROFESSORS_professorFK"))
    private Collection<ProfessorImpl> professors;

	public DisciplineImpl() {
    	
	}
    
    public DisciplineImpl(Integer id, String name, Long version) {
    	this.id = id;
    	this.name = name;
    	this.version = version;
    }
	
	@Override
	public Course getCourse() {
        return course;
    }

    @Override
	public void setCourse(Course course) {
        this.course = course;
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
	public Collection<StudentImpl> getStudents() {
		return students;
	}

	@Override
	public void setStudents(Collection<StudentImpl> students) {
		this.students = students;
	}

	@Override
	public Collection<ProfessorImpl> getProfessors() {
		return professors;
	}

	@Override
	public void setProfessors(Collection<ProfessorImpl> professors) {
		this.professors = professors;
	}

	@Override
	public boolean equals(final Object objectToBeComparated) {			
		
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof DisciplineImpl)) {
			return false;
		}
		
		final DisciplineImpl objectComparatedInstance = (DisciplineImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
				Objects.equals(version, objectComparatedInstance.version);
	}
    
	@Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
