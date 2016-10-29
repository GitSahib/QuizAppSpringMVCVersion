package com.mems.domain;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Model()
	{
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	protected Date updatedTime;
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public Long getId() {
        return id;
    }
	
    public void setId(Long id) {
        this.id = id;
    }
}
