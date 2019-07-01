package br.com.collegesmaster.security.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.collegesmaster.institute.model.entity.Course;
import br.com.collegesmaster.institute.model.entity.impl.CourseImpl;
import br.com.collegesmaster.security.model.entity.Student;
import br.com.collegesmaster.security.model.entity.enums.StudentLevelDesignation;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name="id")
@Audited
@Access(FIELD)
public class StudentImpl extends UserImpl implements Student {

	private static final long serialVersionUID = -6059336830633540020L;
	
	@Column(name = "level")
	private Integer level;
	
	@NotNull(message = "{STUDENT.levelDesignation.notnull}")
	@Enumerated(STRING)
	@Basic(fetch = EAGER, optional = false)
	@Column(name = "levelDesignation", length = 30)
	private StudentLevelDesignation levelDesignation;

    @NotNull
    @ManyToOne(targetEntity = CourseImpl.class, fetch = EAGER, optional = false)
    @JoinColumn(name = "courseFK", referencedColumnName = "id", updatable = false,
    	foreignKey = @ForeignKey(name = "USER_courseFK"))
    private Course course;
    
    @Override
	public Integer getLevel() {
 		return level;
 	}

 	@Override
	public void setLevel(Integer level) {
 		this.level = level;
 	}
 	
	@Override
	public StudentLevelDesignation getLevelDesignation() {
		return levelDesignation;
	}

	@Override
	public void setLevelDesignation(StudentLevelDesignation levelDesignation) {
		this.levelDesignation = levelDesignation;
	}
	
	@Override
	public Course getCourse() {
		return course;
	}

	@Override
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
