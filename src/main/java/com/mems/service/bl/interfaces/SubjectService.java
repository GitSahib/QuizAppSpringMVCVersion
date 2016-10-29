package com.mems.service.bl.interfaces;
import java.util.List;

import com.mems.domain.quiz.Subject;

public interface SubjectService 
{
	List<Subject> searchByKeyword(String word);
	Subject findById(Long Id);
	Subject save(Subject Subject);
	List<Subject> findByMission(String mission);
	void delete(Subject Subject);
	List<Subject> findAll();

}