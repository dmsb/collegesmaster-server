package br.com.collegesmaster.challenge.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.collegesmaster.challenge.model.entity.Badge;
import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.security.model.entity.Professor;
import br.com.collegesmaster.security.model.entity.impl.ProfessorImpl;
import br.com.collegesmaster.security.model.entity.impl.StudentImpl;

@Entity
@Table(name = "badge")
@Audited
@Access(FIELD)
public class BadgeImpl extends ModelImpl implements Badge {

	private static final long serialVersionUID = 1040340295358421616L;

	@NotNull
	@Column(name = "name", length = 150)
	private String name;
	
	@NotEmpty
	@ManyToMany(fetch = LAZY)
	@JoinTable(name = "badges_has_challenges", joinColumns = {
		@JoinColumn(name = "badgeFK", referencedColumnName = "id") }, 
		foreignKey = @ForeignKey(name = "BADGE_HAS_CHALLENGES_badgeFK"),
		inverseJoinColumns = { @JoinColumn(name = "challengeFK", referencedColumnName = "id") }, 
		inverseForeignKey = @ForeignKey(name = "BADGE_HAS_CHALLENGES_challengeFK"))
	private Collection<ChallengeImpl> challenges;
	
	@ManyToMany(mappedBy = "badges", fetch = LAZY)
	private Collection<StudentImpl> students;
	
	@NotNull
	@ManyToOne(targetEntity = ProfessorImpl.class)
	private Professor professor;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Collection<ChallengeImpl> getChallenges() {
		return challenges;
	}

	@Override
	public void setChallenges(Collection<ChallengeImpl> challenges) {
		this.challenges = challenges;
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
	public Professor getProfessor() {
		return professor;
	}

	@Override
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
