package com.mems.controller;

import java.rmi.NoSuchObjectException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mems.domain.quiz.Question;
import com.mems.domain.quiz.QuestionType;
import com.mems.domain.quiz.Subject;
import com.mems.service.bl.interfaces.QuestionService;
import com.mems.service.bl.interfaces.SubjectService;

@Controller
@RequestMapping("/admin")
public class QuestionController extends MEMSController {
	@Autowired 
	QuestionService questionService;
	@Autowired 
	SubjectService subjectService;
	protected QuestionController() {
		super("Questions");
	}
	@ModelAttribute
	public void setTypesAndQuestions(Model model)
	{
		model.addAttribute("types", QuestionType.getNames());
		List<Subject> subjects = subjectService.findAll();
		model.addAttribute("subjects",subjects);
	}
	@RequestMapping(value = {"/question/create"}, method = RequestMethod.GET)
    public String createQuestion(@ModelAttribute("question") Question question,Model model) {
		
		setPageTitle("Create Question");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/question/create";
    }
	
	@RequestMapping(value = {"/question/create"}, method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute("question") Question question,BindingResult result,Model model) {
    	if(result.hasErrors())
    	{
    		setPageTitle("Create Question");
    		model.addAttribute("pageTitle",getPageTitle());
    		return "admin/question/create";
    	}
    	else
    	{
    		questionService.save(question);
    		return "redirect:/admin/question/details/"+question.getId();
    	}
		
    }
	@RequestMapping(value = {"/question/list"}, method = RequestMethod.GET)
    public String listQuestions(Model model) {
		List<Question> list = questionService.findAll();
		model.addAttribute("list",list);
		setPageTitle("List Questions");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/question/list";
    }
	@RequestMapping(value = {"/question/details/{id}"}, method = RequestMethod.GET)
    public String createQuestion(@PathVariable("id") Long id,Model model) {
		Question question  = questionService.findById(id);
		model.addAttribute("question",question);
		setPageTitle("Create Details");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/question/details";
    }
	
	@RequestMapping(value = { "/question/edit/{id}" }, method = RequestMethod.GET)
	public String editQuestion(@PathVariable("id") Long id, Model model) throws NoSuchObjectException {

		setPageTitle("Edit Question");
		model.addAttribute("pageTitle",getPageTitle());
		Question question = questionService.findById(id);
		if(question == null)
		{
			throw new NoSuchObjectException("Question with id:"+id+" Not Found");
		}
		model.addAttribute("question",question);
		return "admin/question/edit";
	}
	@RequestMapping(value = { "/question/delete/{id}" }, method = RequestMethod.GET)
	public String deleteQuestion(@PathVariable("id") Long id, Model model) throws NoSuchObjectException {

		Question question = questionService.findById(id);
		if(question == null)
		{
			throw new NoSuchObjectException("Question with id:"+id+" Not Found");
		}
		questionService.delete(question);
		return "redirect:/admin/question/list";
	}
	@RequestMapping(value = { "/question/edit" }, method = RequestMethod.POST)
	public String editQuestion(@ModelAttribute("question") Question question, BindingResult result,Model model) {
		if (result.hasErrors()) {
			setPageTitle("Edit Question");
			model.addAttribute("pageTitle",getPageTitle());
			return "admin/question/edit";
		} else {
			questionService.update(question);
			return "redirect:/admin/question/details/" + question.getId();
		}

	}

}
