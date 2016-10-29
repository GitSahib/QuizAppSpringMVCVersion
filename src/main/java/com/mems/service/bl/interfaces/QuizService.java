package com.mems.service.bl.interfaces;
import java.util.List;

import com.mems.domain.QuizApplicationForm;
import com.mems.domain.quiz.Quiz;
public interface QuizService 
{	
	Quiz createQuize(QuizApplicationForm quizAF);

	Quiz findById(Long id);

	List<Quiz> findAll();

	void delete(Quiz question);

	Quiz update(QuizApplicationForm quizAF);

	QuizApplicationForm getForm(Quiz quiz);

}