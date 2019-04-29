package br.com.collegesmaster.challenge.model.entity.impl;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.collegesmaster.challenge.model.entity.Alternative;
import br.com.collegesmaster.challenge.model.entity.Question;
import br.com.collegesmaster.challenge.model.entity.enums.Letter;
import br.com.collegesmaster.generics.model.impl.ModelImpl;

@Entity
@Table(name = "alternative")
@Access(FIELD)
@Audited
public class AlternativeImpl extends ModelImpl implements Alternative {

	private static final long serialVersionUID = -9207076283580095871L;
	
	@NotNull(message = "{ALTERNATIVE.letter.notnull}")
	@Enumerated(STRING)
	@Basic(fetch = LAZY, optional = false)
	@Column(name = "letter", length = 1)
	private Letter letter;
	
	@NotNull(message = "{ALTERNATIVE.description.notnull}")
    @Lob
    @Column(name = "description", length = 150, nullable = false, columnDefinition = "text")
    private String description;
    
	@NotNull(message = "{ALTERNATIVE.is_true.notnull}")
	@Column(name = "isTrue", nullable = false)
	private Boolean isTrue;
	
    @JsonBackReference
    @NotNull(message = "{ALTERNATIVE.question.notnull}")
	@ManyToOne(targetEntity = QuestionImpl.class, optional = false, fetch = LAZY)
	@JoinColumn(name = "questionFK", referencedColumnName = "id", 
		foreignKey = @ForeignKey(name = "ALTERNATIVE_questionFK"))
	private Question question;
	
	@Override
	public Letter getLetter() {
		return letter;
	}

	@Override
	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Boolean getIsTrue() {
		return isTrue;
	}

	@Override
	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}
	
	@Override
	public Question getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	@Override
	public boolean equals(final Object objectToBeComparated) {
		
		if(this == objectToBeComparated) {
			return true;
		}
		
		if(!(objectToBeComparated instanceof AlternativeImpl)) {
			return false;
		}
		
		final AlternativeImpl objectComparatedInstance = (AlternativeImpl) objectToBeComparated;
		
		return Objects.equals(this.id, objectComparatedInstance.id) && 
			    Objects.equals(description, objectComparatedInstance.description) &&
			    Objects.equals(isTrue, objectComparatedInstance.isTrue);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id, description, isTrue);
    }
}
