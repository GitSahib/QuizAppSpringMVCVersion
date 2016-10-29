package com.mems.domain.quiz;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.mems.domain.Model;

@Entity

public class Subject extends Model{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
    @Size(max = 200,message="name.empty.or.greater")
    private String name;
	
    @NotEmpty(message="mission.empty")
    private String mission;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Question> questions;
    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mission
	 */
	public String getMission() {
		return mission;
	}

	/**
	 * @param mission the mission to set
	 */
	public void setMission(String mission) {
		this.mission = mission;
	}

	

	/**
	 * @return the Questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param Questions the Questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	
}
