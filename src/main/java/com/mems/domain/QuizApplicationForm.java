package com.mems.domain;

import java.util.Date;

import com.mems.domain.quiz.Subject;

public class QuizApplicationForm {
	private int shortQuestions;
	private int longQuestions;
	private int mcqs;
	private int trueFalse;
	private int fillBlanks;
	private Subject subject;
	
	private Date startTime;
	private Date endTime;
	private User user;
	
	private String title;
	private String description;
	private Long id;
	/**
	 * @return the shortQuestions
	 */
	public int getShortQuestions() {
		return shortQuestions;
	}
	/**
	 * @param shortQuestions the shortQuestions to set
	 */
	public void setShortQuestions(int shortQuestions) {
		this.shortQuestions = shortQuestions;
	}
	/**
	 * @return the longQuestions
	 */
	public int getLongQuestions() {
		return longQuestions;
	}
	/**
	 * @param longQuestions the longQuestions to set
	 */
	public void setLongQuestions(int longQuestions) {
		this.longQuestions = longQuestions;
	}
	/**
	 * @return the mcqs
	 */
	public int getMcqs() {
		return mcqs;
	}
	/**
	 * @param mcqs the mcqs to set
	 */
	public void setMcqs(int mcqs) {
		this.mcqs = mcqs;
	}
	/**
	 * @return the trueFalse
	 */
	public int getTrueFalse() {
		return trueFalse;
	}
	/**
	 * @param trueFalse the trueFalse to set
	 */
	public void setTrueFalse(int trueFalse) {
		this.trueFalse = trueFalse;
	}
	/**
	 * @return the fillBlanks
	 */
	public int getFillBlanks() {
		return fillBlanks;
	}
	/**
	 * @param fillBlanks the fillBlanks to set
	 */
	public void setFillBlanks(int fillBlanks) {
		this.fillBlanks = fillBlanks;
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
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
}
