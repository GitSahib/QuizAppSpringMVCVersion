package com.mems.domain.quiz;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mems.domain.Model;

@Entity
public class Question extends Model{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    private Quiz quiz;
	@Enumerated(EnumType.STRING)
	private QuestionType type;
	@NotNull
    @Size(max = 3000)
    private String text;
    @OneToMany(fetch=FetchType.EAGER)
    private List<Choice> choices = new ArrayList<Choice>();
    private int totalMarks;
    @ManyToOne
    private Subject subject;
    /**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	
    //region - Getters And Setters
    /**
	 * @return the quiz
	 */
	public Quiz getQuiz() {
		return quiz;
	}

	/**
	 * @param quiz the quiz to set
	 */
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

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
	 * @return the choices
	 */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
	 * @param choices the choices to set
	 */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	
	public Question clone()
	{
		return this;
	}

	/**
	 * @return the type
	 */
	public QuestionType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(QuestionType type) {
		this.type = type;
	}
	//endregion

	/**
	 * @return the totalMarks
	 */
	public int getTotalMarks() {
		return totalMarks;
	}

	/**
	 * @param totalMarks the totalMarks to set
	 */
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
}