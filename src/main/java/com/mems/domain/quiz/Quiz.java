package com.mems.domain.quiz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mems.domain.Model;
import com.mems.domain.User;

@Entity
@Table(name = "quiz")
public class Quiz extends Model{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	List<Question> trueFalse;
	@Transient
	List<Question> mcqs;
	@Transient
	List<Question> blanks;
	@Transient
	List<Question> shortQuestions;
	@Transient
	List<Question> longQuestions;
	
	/**
	 * @return the trueFalse
	 */
	public List<Question> getTrueFalse() {
		trueFalse = new ArrayList<Question>();
		for(Question q:questions)
		{
			if(q.getType() == QuestionType.TRUE_FALSE)
			{
				trueFalse.add(q);
			}
		}
		return trueFalse;
	}

	/**
	 * @param trueFalse the trueFalse to set
	 */
	public void setTrueFalse(List<Question> trueFalse) {
		this.trueFalse = trueFalse;
	}

	/**
	 * @return the mcqs
	 */
	public List<Question> getMcqs() {
		mcqs = new ArrayList<Question>();
		for(Question q:questions)
		{
			if(q.getType() == QuestionType.MULTIPLE_CHOICE)
			{
				mcqs.add(q);
			}
		}
		return mcqs;
	}

	/**
	 * @param mcqs the mcqs to set
	 */
	public void setMcqs(List<Question> mcqs) {
		this.mcqs = mcqs;
	}

	/**
	 * @return the blanks
	 */
	public List<Question> getBlanks() {
		blanks = new ArrayList<Question>();
		for(Question q:questions)
		{
			if(q.getType() == QuestionType.FILL_BLANK)
			{
				blanks.add(q);
			}
		}
		return blanks;
	}

	/**
	 * @param blanks the blanks to set
	 */
	public void setBlanks(List<Question> blanks) {
		this.blanks = blanks;
	}

	/**
	 * @return the shortQuestions
	 */
	public List<Question> getShortQuestions() {
		shortQuestions = new ArrayList<Question>();
		for(Question q:questions)
		{
			if(q.getType() == QuestionType.SHORT_QUESTION)
			{
				shortQuestions.add(q);
			}
		}
		return shortQuestions;
	}

	/**
	 * @param shortQuestions the shortQuestions to set
	 */
	public void setShortQuestions(List<Question> shortQuestions) {
		this.shortQuestions = shortQuestions;
	}

	/**
	 * @return the longQuestions
	 */
	public List<Question> getLongQuestions() {
		longQuestions = new ArrayList<Question>();
		for(Question q:questions)
		{
			if(q.getType() == QuestionType.LONG_QUESTION)
			{
				longQuestions.add(q);
			}
		}
		return longQuestions;
	}

	/**
	 * @param longQuestions the longQuestions to set
	 */
	public void setLongQuestions(List<Question> longQuestions) {
		this.longQuestions = longQuestions;
	}

	@NotNull

	@Size(max = 200)
	private String title;
	
	@OneToMany
	@JoinTable
	List<Question> questions;
	
	
	@NotNull
	@Size(max = 500)
	private String description;
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

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

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	Date startTime;
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	Date endTime;
	
	@OneToOne
	User user;
	
	@OneToOne
	Subject subject;
	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Quiz [title=" + title + ", description=" + description + "]";
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


}
