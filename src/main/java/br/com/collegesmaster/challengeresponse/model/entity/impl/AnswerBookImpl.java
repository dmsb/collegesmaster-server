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
import br.com.collegesmaster.challengeresponse.model.entity.AnswerBook;
import br.com.collegesmaster.challengeresponse.model.entity.QuestionOfAnswerBook;
import br.com.collegesmaster.generics.model.impl.ModelImpl;
import br.com.collegesmaster.security.model.entity.Student;
import br.com.collegesmaster.security.model.entity.impl.UserImpl;

@Entity
@Table(name = "answer_book",
	uniqueConstraints = @UniqueConstraint(columnNames = {"challengeFK", "userFK"},  name = "UK_CHALLENGE_USER"))
@Access(FIELD)
@Audited
public class AnswerBookImpl extends ModelImpl implements AnswerBook {

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
	private Student owner;
	
	@NotNull
	@Column(name = "score", nullable = false, length = 11)
	private Integer score;
	
	@NotEmpty
	@NotAudited
	@OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true, mappedBy = "answerBook")
	private Collection<QuestionOfAnswerBookImpl> questionsOfAnswerBook;
	
	@Override
	@PrePersist
	@PreUpdate
	public void calculateScore() {
		score = 0;
		questionsOfAnswerBook.stream()
			.forEach(response -> { selectQuestionToProcessScore(response); });
	}

	private void selectQuestionToProcessScore(final QuestionOfAnswerBook response) {
		response.getTargetQuestion()
		.getAlternatives().stream()
			.forEach(alternative -> {					
			addScore(response, alternative);
		});
	}

	@Override
	public void addScore(final QuestionOfAnswerBook response, final AlternativeImpl alternative) {
		
		final Alternative selectedAlternative = response.getSelectedAlternatives()
				.stream().findFirst().orElse(null);
		if(alternative.getIsTrue() && alternative.getLetter().equals(selectedAlternative.getLetter())) {
			score = score + response.getTargetQuestion().getScore();
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
	public Student getOwner() {
		return owner;
	}

	@Override
	public void setOwner(Student owner) {
		this.owner = owner;
	}

	@Override
	public Integer getScore() {
		return score;
	}

	@Override
	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public Collection<QuestionOfAnswerBookImpl> getQuestionsOfAnswerBook() {
		return questionsOfAnswerBook;
	}

	@Override
	public void setQuestionOfAnswerBook(Collection<QuestionOfAnswerBookImpl> questionsResponse) {
		this.questionsOfAnswerBook = questionsResponse;
	}
	
	@Override
	public boolean equals(final Object objectToBeComparated) {

		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof AnswerBookImpl)) {
			return false;
		}
		
		final AnswerBookImpl objectComparatedInstance = (AnswerBookImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
				Objects.equals(this.score, objectComparatedInstance.score);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, score);
    }
	
}
