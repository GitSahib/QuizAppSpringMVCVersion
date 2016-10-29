package com.mems.service.bl.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mems.domain.quiz.Choice;
import com.mems.domain.quiz.Question;
import com.mems.domain.quiz.QuestionType;
import com.mems.domain.quiz.Subject;
import com.mems.repository.ChoiceRepository;
import com.mems.repository.QuestionRepository;
import com.mems.service.bl.interfaces.QuestionService;
@PreAuthorize("Teacher")
@Transactional
@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionDao;
    @Autowired
    private ChoiceRepository choiceDao;
	@Override
	public List<Question> searchByKeyword(String word) {
		// TODO Auto-generated method stub
		return questionDao.searchByKeyword(word);
	}

	@Override
	public Question findById(Long Id) {
		// TODO Auto-generated method stub
		return questionDao.findById(Id);
	}

	@Override
	public List<Question> findBySubject(Subject subject) {
		// TODO Auto-generated method stub
		return questionDao.findBySubject(subject);
	}
	
	@Override
	public Question save(Question question) {
		
		Question q =  questionDao.save(question);
		for(Choice choice:question.getChoices())
		{
			choice.setQuestion(q);
			choiceDao.save(choice);
		}
		return q;
	
	}
	@Override
	public Question update(Question question) {		
		
		for(Choice choice:question.getChoices())
		{
			choice.setQuestion(question);
			choiceDao.save(choice);
		}
		Question q =  questionDao.save(question);
		return q;
	
	}

	@Override
	public void delete(Question question) {
		// TODO Auto-generated method stub
		for(Choice ch:question.getChoices())
		{
			choiceDao.delete(ch);
		}
		questionDao.delete(question);
		
	}

	@Override
	public List<Question> findByType(QuestionType type) {
		// TODO Auto-generated method stub
		return questionDao.findByType(type);
	}

	@Override
	public List<Question> findAll() {
		// TODO Auto-generated method stub
		return questionDao.findAll();
	}

	
	
	

} // The End of Class;