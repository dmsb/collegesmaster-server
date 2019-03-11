package br.com.collegesmaster.challengeresponse.model.entity.impl;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.collegesmaster.challenge.model.entity.Alternative;
import br.com.collegesmaster.challenge.model.entity.Challenge;
import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.ChallengeImpl;
import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.challengeresponse.model.entity.QuestionResponse;
import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.security.model.entity.User;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;

@Entity
@Table(name = "challenge_response",
	uniqueConstraints = @UniqueConstraint(columnNames = {"challengeFK", "userFK"},  name = "UK_CHALLENGE_USER"))
@Access(FIELD)
@Audited
public class ChallengeResponseImpl extends ModelImpl implements ChallengeResponse {

	private static final long serialVersionUID = -4223636598786128623L;
	
	@NotNull
	@ManyToOne(targetEntity = ChallengeImpl.class, optional = false, fetch = EAGER)
	@JoinColumn(name = "challengeFK", referencedColumnName = "id", updatable = false, nullable = false,
		foreignKey = @ForeignKey(name = "CR_challengeFK"))
	private Challenge challenge;
	
	@NotNull
	@ManyToOne(targetEntity = UserImpl.class, optional = false, fetch = LAZY)
	@JoinColumn(name = "userFK", referencedColumnName = "id", updatable = false, nullable = false,
		foreignKey = @ForeignKey(name = "CR_userFK"))
	private User user;
	
	@NotNull
	@Column(name = "punctuation", nullable = false, length = 11)
	private Integer punctuation;
	
	@NotEmpty
	@NotAudited
	@OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true, mappedBy = "challengeResponse")
	private Collection<QuestionResponseImpl> questionsResponse;
	
	@Override
	@PrePersist
	@PreUpdate
	public void calculatePunctuation() {
		punctuation = 0;
		questionsResponse.stream()
			.forEach(response -> { selectQuestionToProcessPunctuation(response); });
	}

	private void selectQuestionToProcessPunctuation(final QuestionResponse response) {
		response.getTargetQuestion()
		.getAlternatives().stream()
			.forEach(alternative -> {					
			addPunctuation(response, alternative);
		});
	}

	@Override
	public void addPunctuation(final QuestionResponse response, final AlternativeImpl alternative) {
		
		final Alternative selectedAlternative = response.getSelectedAlternatives()
				.stream().findFirst().orElse(null);
		if(alternative.getIsTrue() && alternative.getLetter().equals(selectedAlternative.getLetter())) {
			punctuation = punctuation + response.getTargetQuestion().getPunctuation();
		}
	}

	@Override
	public Challenge getChallenge() {
		return challenge;
	}

	@Override
	public void setChallenge(Challenge targetChallenge) {
		this.challenge = targetChallenge;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Integer getPunctuation() {
		return punctuation;
	}

	@Override
	public void setPunctuation(Integer punctuation) {
		this.punctuation = punctuation;
	}

	@Override
	public Collection<QuestionResponseImpl> getQuestionsResponse() {
		return questionsResponse;
	}

	@Override
	public void setQuestionsResponse(Collection<QuestionResponseImpl> questionsResponse) {
		this.questionsResponse = questionsResponse;
	}
	
	@Override
	public boolean equals(final Object objectToBeComparated) {

		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof ChallengeResponseImpl)) {
			return false;
		}
		
		final ChallengeResponseImpl objectComparatedInstance = (ChallengeResponseImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
				Objects.equals(this.punctuation, objectComparatedInstance.punctuation);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, punctuation);
    }
	
}
