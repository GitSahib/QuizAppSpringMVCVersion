package com.mems.domain.quiz;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mems.domain.Model;

@Entity
public class Choice extends Model {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    @Size(max = 300)
    private String text;

    @NotNull
    private Boolean correct;
    private int choiceMarks;
    @NotNull
    @ManyToOne
    private Question question;

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the correct
	 */
	public Boolean getCorrect() {
		return correct;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the choiceMarks
	 */
	public int getChoiceMarks() {
		return choiceMarks;
	}

	/**
	 * @param choiceMarks the choiceMarks to set
	 */
	public void setChoiceMarks(int choiceMarks) {
		this.choiceMarks = choiceMarks;
	}
}