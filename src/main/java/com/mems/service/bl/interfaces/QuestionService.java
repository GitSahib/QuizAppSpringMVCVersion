package com.mems.service.bl.interfaces;
import java.util.List;

import com.mems.domain.quiz.Question;
import com.mems.domain.quiz.QuestionType;
import com.mems.domain.quiz.Subject;

public interface QuestionService 
{
	List<Question> searchByKeyword(String word);
	Question findById(Long Id);
	Question save(Question Question);
	List<Question> findByType(QuestionType question);
	List<Question> findBySubject(Subject subject);
	void delete(Question question);
	List<Question> findAll();
	Question update(Question question);

}