package com.mems.domain.quiz;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.mems.domain.Model;
import com.mems.domain.User;
@Entity
public class Student extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany
	@JoinTable
	private List<User> teacher;
	@OneToMany
	@JoinTable
	private List<Subject> subject;
	@OneToOne
	Student student;
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	@Temporal(TemporalType.DATE)
	@NotNull
	Date startDate;
	@Temporal(TemporalType.DATE)
	@NotNull
	Date endDate;
	/**
	 * @return the teacher
	 */
	public List<User> getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(List<User> teacher) {
		this.teacher = teacher;
	}
	/**
	 * @return the subject
	 */
	public List<Subject> getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
