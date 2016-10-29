package com.mems.service.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mems.domain.QuizApplicationForm;
import com.mems.domain.quiz.Question;
import com.mems.domain.quiz.QuestionType;
import com.mems.domain.quiz.Quiz;
import com.mems.repository.QuestionRepository;
import com.mems.repository.QuizeRepository;
import com.mems.service.bl.interfaces.QuizService;

@PreAuthorize("Teacher")
@Transactional
@Service
public class QuizeServiceImpl implements QuizService {

	@Autowired
	private QuizeRepository quizDao;
	@Autowired
	private QuestionRepository questionDao;

	public Quiz createQuize(QuizApplicationForm quizAF) {
		// TODO Auto-generated method stub
		Quiz quiz = new Quiz();
		int index = 0;
		int range = 0;
		Random rand = new Random();

		List<Question> all = new ArrayList<Question>();
		
		/*###########MCQS#####################*/
		List<Question> multipleChoice = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.MULTIPLE_CHOICE);
		
		if (multipleChoice.size() > quizAF.getMcqs()) {
			range = quizAF.getMcqs();
			for (int i = 0; i < range; i++) 
			{
				index = rand.nextInt(multipleChoice.size());
				all.add(multipleChoice.get(index));
				multipleChoice.remove(index);
			}
		} else {
			if(multipleChoice.size() != 0)
			all.addAll(multipleChoice);
		}

		/*###########Short Questions#####################*/
		List<Question> shortQuestions = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.SHORT_QUESTION);

		if (shortQuestions.size() > quizAF.getShortQuestions()) {
			range = quizAF.getShortQuestions();
			for (int i = 0; i < range; i++) {
				index = rand.nextInt(shortQuestions.size());
				all.add(shortQuestions.get(index));
				shortQuestions.remove(index);
			}
		} else {
			if(shortQuestions.size() != 0)
			all.addAll(shortQuestions);
		}

		/*###########True False#####################*/
		List<Question> trueFalse = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.TRUE_FALSE);

		if (trueFalse.size() > quizAF.getTrueFalse()) {
			range = quizAF.getTrueFalse();
			for (int i = 0; i < range; i++) {
				index = rand.nextInt(range);
				all.add(trueFalse.get(index));
				trueFalse.remove(index);
			}
		}
		else
		{
			if(trueFalse.size() != 0)
			all.addAll(trueFalse);
		}
		
		/*###########Long Questions#####################*/
		List<Question> longQuestions = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.LONG_QUESTION);
		
		if(longQuestions.size() > quizAF.getLongQuestions())
		{
			range = quizAF.getLongQuestions();
			for (int i = 0; i < range; i++) {
				index = rand.nextInt(longQuestions.size());
				all.add(longQuestions.get(index));
				longQuestions.remove(index);
			}
		}
		else
		{
			if(longQuestions.size() != 0)
			all.addAll(longQuestions);
		}
		
		/*###########Blanks#####################*/

		List<Question> fillBlanks = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.FILL_BLANK);
		if(fillBlanks.size() > quizAF.getFillBlanks())
		{
			range =  quizAF.getFillBlanks();

			for (int i = 0; i < range; i++) {
				index = rand.nextInt(fillBlanks.size());
				all.add(fillBlanks.get(index));
				fillBlanks.remove(index);
			}
		}
		else
		{		
			if(fillBlanks.size() != 0)
			all.addAll(fillBlanks);
		}

		

		quiz.setQuestions(all);
		quiz.setStartTime(quizAF.getStartTime());
		quiz.setEndTime(quizAF.getEndTime());
		quiz.setSubject(quizAF.getSubject());
		quiz.setTitle(quizAF.getTitle());
		quiz.setDescription(quizAF.getDescription());
		quiz.setUser(quizAF.getUser());

		return quizDao.save(quiz);

	}

	@Override
	public Quiz findById(Long id) {
		// TODO Auto-generated method stub
		return quizDao.findOne(id);
	}

	@Override
	public List<Quiz> findAll() {
		// TODO Auto-generated method stub
		return quizDao.findAll();
	}

	@Override
	public void delete(Quiz quiz) {
		// TODO Auto-generated method stub
		quizDao.delete(quiz);
	}

	@Override
	public Quiz update(QuizApplicationForm quizAF) {
		// TODO Auto-generated method stub
		Quiz quiz = quizDao.findOne(quizAF.getId());
		int index = 0;
		int range = 0;
		Random rand = new Random();

		List<Question> all = new ArrayList<Question>();
		
		/*###########MCQS#####################*/
		List<Question> multipleChoice = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.MULTIPLE_CHOICE);
		
		if (multipleChoice.size() > quizAF.getMcqs()) {
			range = quizAF.getMcqs();
			for (int i = 0; i < range; i++) 
			{
				index = rand.nextInt(multipleChoice.size());
				all.add(multipleChoice.get(index));
				multipleChoice.remove(index);
			}
		} else {
			if(multipleChoice.size() != 0)
			all.addAll(multipleChoice);
		}

		/*###########Short Questions#####################*/
		List<Question> shortQuestions = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.SHORT_QUESTION);

		if (shortQuestions.size() > quizAF.getShortQuestions()) {
			range = quizAF.getShortQuestions();
			for (int i = 0; i < range; i++) {
				index = rand.nextInt(shortQuestions.size());
				all.add(shortQuestions.get(index));
				shortQuestions.remove(index);
			}
		} else {
			if(shortQuestions.size() != 0)
			all.addAll(shortQuestions);
		}

		/*###########True False#####################*/
		List<Question> trueFalse = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.TRUE_FALSE);

		if (trueFalse.size() > quizAF.getTrueFalse()) {
			range = quizAF.getTrueFalse();
			for (int i = 0; i < range; i++) {
				index = rand.nextInt(range);
				all.add(trueFalse.get(index));
				trueFalse.remove(index);
			}
		}
		else
		{
			if(trueFalse.size() != 0)
			all.addAll(trueFalse);
		}
		
		/*###########Long Questions#####################*/
		List<Question> longQuestions = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.LONG_QUESTION);
		
		if(longQuestions.size() > quizAF.getLongQuestions())
		{
			range = quizAF.getLongQuestions();
			for (int i = 0; i < range; i++) {
				index = rand.nextInt(longQuestions.size());
				all.add(longQuestions.get(index));
				longQuestions.remove(index);
			}
		}
		else
		{
			if(longQuestions.size() != 0)
			all.addAll(longQuestions);
		}
		
		/*###########Blanks#####################*/

		List<Question> fillBlanks = questionDao.findRandomQuestionBySubejctAndType(quizAF.getSubject(),
				QuestionType.FILL_BLANK);
		if(fillBlanks.size() > quizAF.getFillBlanks())
		{
			range =  quizAF.getFillBlanks();

			for (int i = 0; i < range; i++) {
				index = rand.nextInt(fillBlanks.size());
				all.add(fillBlanks.get(index));
				fillBlanks.remove(index);
			}
		}
		else
		{		
			if(fillBlanks.size() != 0)
			all.addAll(fillBlanks);
		}

		

		quiz.setQuestions(all);
		quiz.setStartTime(quizAF.getStartTime());
		quiz.setEndTime(quizAF.getEndTime());
		quiz.setSubject(quizAF.getSubject());
		quiz.setTitle(quizAF.getTitle());
		quiz.setDescription(quizAF.getDescription());
		quiz.setUser(quizAF.getUser());

		return quizDao.save(quiz);
	}

	@Override
	public QuizApplicationForm getForm(Quiz quiz) {
		// TODO Auto-generated method stub
		QuizApplicationForm qaf = new 	QuizApplicationForm ();
		qaf.setId(quiz.getId());
		qaf.setTitle(quiz.getTitle());
		qaf.setDescription(quiz.getDescription());
		qaf.setStartTime(quiz.getStartTime());
		qaf.setEndTime(quiz.getEndTime());
		qaf.setTrueFalse(quiz.getTrueFalse().size());
		qaf.setFillBlanks(quiz.getBlanks().size());
		qaf.setMcqs(quiz.getMcqs().size());
		qaf.setShortQuestions(quiz.getShortQuestions().size());
		qaf.setLongQuestions(quiz.getLongQuestions().size());
		qaf.setSubject(quiz.getSubject());
		qaf.setUser(quiz.getUser());
		return qaf;
	}

} // The End of Class;