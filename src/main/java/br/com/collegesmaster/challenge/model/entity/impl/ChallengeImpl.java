package br.com.collegesmaster.challenge.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.enums.ChallengeType;
import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.institute.model.entity.Discipline;
import br.com.collegesmaster.institute.model.entity.impl.DisciplineImpl;
import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;

@Entity
@Table(name = "challenge")
@Access(FIELD)
@Audited
public class ChallengeImpl extends ModelImpl implements Challenge {

	private static final long serialVersionUID = 6314730845000580522L;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name= "title", nullable = false, length = 30)
	private String title;
	
	@NotNull
	@ManyToOne(targetEntity = UserImpl.class, optional = false, fetch = EAGER)
	@JoinColumn(name = "userFK", referencedColumnName = "id", updatable = false,
		foreignKey = @ForeignKey(name = "CHALLENGE_userFK"))
	private User user;
	
	@NotNull
	@ManyToOne(targetEntity = DisciplineImpl.class, optional = false, fetch = EAGER)
	@JoinColumn(name = "disciplineFK", referencedColumnName = "id", 
		foreignKey = @ForeignKey(name = "CHALLENGE_disciplineFK"))
	private Discipline discipline;
	
	@JsonManagedReference
	@NotNull
	@NotAudited
	@OneToMany(targetEntity = QuestionImpl.class, cascade = ALL, fetch = LAZY, 
		orphanRemoval = true, mappedBy="challenge")
	private Collection<QuestionImpl> questions;
	
	@NotNull
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	@NotNull
	@Enumerated(STRING)
	@Basic(fetch = LAZY, optional = false)
	@Column(name = "challengeType", length = 15, nullable = false)
	private ChallengeType challengeType;
	
	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public Discipline getDiscipline() {
		return discipline;
	}

	@Override
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	@Override
	public Collection<QuestionImpl> getQuestions() {
		return questions;
	}

	@Override
	public void setQuestions(Collection<QuestionImpl> questions) {
		this.questions = questions;
	}

	@Override
	public Boolean getEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public ChallengeType getChallengeType() {
		return challengeType;
	}

	@Override
	public void setChallengeType(ChallengeType challengeType) {
		this.challengeType = challengeType;
	}

	@Override
	public boolean equals(final Object objectToBeComparated) {
		
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof ChallengeImpl)) {
			return false;
		}
		
		final ChallengeImpl objectComparatedInstance = (ChallengeImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
			    Objects.equals(this.title, objectComparatedInstance.title);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.title);
    }
}
