package br.com.collegesmaster.challengeresponse.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.LAZY;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.impl.AlternativeImpl;
import br.com.collegesmaster.challenge.model.entity.impl.QuestionImpl;
import br.com.collegesmaster.challengeresponse.model.entity.ChallengeResponse;
import br.com.collegesmaster.challengeresponse.model.entity.QuestionResponse;
import br.com.collegesmaster.generics.model.impl.ModelImpl;

@Entity
@Table(name = "question_response")
@Access(FIELD)
@Audited
public class QuestionResponseImpl extends ModelImpl implements QuestionResponse {
	
	private static final long serialVersionUID = 693650150648888820L;
	
	@NotNull
	@ManyToOne(targetEntity = ChallengeResponseImpl.class, optional = false, fetch = LAZY)
	@JoinColumn(name = "challengeResponseFK", referencedColumnName = "id", updatable = false,
		foreignKey = @ForeignKey(name = "QUESTION_RESPONSE_challengeResponseFK"))
	private ChallengeResponse challengeResponse;
	
	@NotNull
	@ManyToOne(targetEntity = QuestionImpl.class, fetch = LAZY, optional = false)
	@JoinColumn(name = "targetQuestionFK", referencedColumnName = "id", updatable = false,
		foreignKey = @ForeignKey(name = "QUESTION_RESPONSE_targetQuestionFK"))
	private Question targetQuestion;
	
	@ManyToMany(fetch = LAZY)
	@JoinTable(name="question_response_has_alternatives",
	    joinColumns = {@JoinColumn(name="questionResponseFK", referencedColumnName = "id")},
	    foreignKey = @ForeignKey(name = "QUESTION_RESPONSE_HAS_ALTERNATIVES_questionResponseFK"),
	    inverseJoinColumns = {@JoinColumn(name="selectedAlternativeFK", referencedColumnName = "id")},
	    inverseForeignKey = @ForeignKey(name = "QUESTION_RESPONSE_HAS_ALTERNATIVES_selectedAlternativeFK"))
	private Collection<AlternativeImpl> selectedAlternatives;
	
	@Override
	public ChallengeResponse getChallengeResponse() {
		return challengeResponse;
	}

	@Override
	public void setChallengeResponse(ChallengeResponse challengeResponse) {
		this.challengeResponse = challengeResponse;
	}

	@Override
	public Question getTargetQuestion() {
		return targetQuestion;
	}

	@Override
	public void setTargetQuestion(Question targetQuestion) {
		this.targetQuestion = targetQuestion;
	}
	
	@Override
	public Collection<AlternativeImpl> getSelectedAlternatives() {
		return selectedAlternatives;
	}

	@Override
	public void setSelectedAlternatives(Collection<AlternativeImpl> selectedAlternatives) {
		this.selectedAlternatives = selectedAlternatives;
	}

	@Override
	public boolean equals(final Object objectToBeComparated) {
		
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof QuestionImpl)) {
			return false;
		}
		
		final QuestionResponseImpl objectComparatedInstance = (QuestionResponseImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) &&
				Objects.equals(this.version, objectComparatedInstance.version);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
