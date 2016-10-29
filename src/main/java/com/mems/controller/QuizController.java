package com.mems.controller;

import java.rmi.NoSuchObjectException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mems.domain.QuizApplicationForm;
import com.mems.domain.User;
import com.mems.domain.quiz.Quiz;
import com.mems.domain.quiz.Subject;
import com.mems.service.bl.interfaces.QuizService;
import com.mems.service.bl.interfaces.SubjectService;
import com.mems.service.security.interfaces.UserService;

@Controller
@RequestMapping("/admin/quiz")
public class QuizController  extends MEMSController {
	protected QuizController() {
		super("Profile");
		// TODO Auto-generated constructor stub
	}

	Logger log = Logger.getLogger(getClass());

	@Autowired 
	QuizService quizService;
	@Autowired 
	SubjectService subjectService;
	@Autowired
	UserService userService;
	//@RequestMapping("")
	@ModelAttribute
	public void setSubjects(Model model)
	{
		List<Subject> subjects = subjectService.findAll();
		model.addAttribute("subjects",subjects);
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createQuize(@ModelAttribute("quizForm") QuizApplicationForm quizeAF)
	{
		return "admin/quiz/create";
	}
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createQuize(@Valid @ModelAttribute("quizForm") QuizApplicationForm quizeAF,BindingResult bindingResult)
	{
		User user = userService.getLoggedInUser();
		quizeAF.setUser(user);
		Quiz quiz = quizService.createQuize(quizeAF);
		return "redirect:/admin/details/"+quiz.getId();
	}
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String createQuize(@PathVariable("id") Long id,Model model)
	{
		Quiz quiz = quizService.findById(id);
		model.addAttribute("quiz",quiz);
		return "admin/quiz/details";
	}
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listQuestions(Model model) {
		List<Quiz> list = quizService.findAll();
		model.addAttribute("list",list);
		setPageTitle("List Quiz");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/quiz/list";
    }
	
	
	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String editQuestion(@PathVariable("id") Long id, Model model) throws NoSuchObjectException {

		setPageTitle("Edit Quiz");
		model.addAttribute("pageTitle",getPageTitle());
		Quiz quiz = quizService.findById(id);
		QuizApplicationForm quizAF = quizService.getForm(quiz);
		if(quiz == null)
		{
			throw new NoSuchObjectException("Quiz with id:"+id+" Not Found");
		}
		model.addAttribute("quizForm",quizAF);
		return "admin/quiz/edit";
	}
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
	public String deleteQuestion(@PathVariable("id") Long id, Model model) throws NoSuchObjectException {

		Quiz quiz = quizService.findById(id);
	
		if(quiz == null)
		{
			throw new NoSuchObjectException("Quiz with id:"+id+" Not Found");
		}
		quizService.delete(quiz);
		return "redirect:/admin/list";
	}
	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	public String editQuestion(@Valid @ModelAttribute("quizForm") QuizApplicationForm quizeAF, BindingResult result,Model model) {
		if (result.hasErrors()) {
			setPageTitle("Edit Quiz");
			model.addAttribute("pageTitle",getPageTitle());
			return "admin/quiz/edit";
		} else {
			quizService.update(quizeAF);
			return "redirect:/admin/quiz/details/" + quizeAF.getId();
		}

	}
	
}
