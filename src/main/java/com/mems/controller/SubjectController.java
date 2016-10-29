package com.mems.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mems.domain.quiz.Subject;
import com.mems.service.bl.interfaces.SubjectService;

import javassist.NotFoundException;

@Controller
@RequestMapping("/admin/subject")
public class SubjectController extends MEMSController {
	@Autowired
	SubjectService subjectService;

	protected SubjectController() {
		super("Subjects");
	}
	// Subject Routes
	
	@RequestMapping(value = { "/create" }, method = RequestMethod.GET)
	public String createSubject(@ModelAttribute("subject") Subject subject, Model model) {

		setPageTitle("Create Subject");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/create";
	}

	@RequestMapping(value = { "/create" }, method = RequestMethod.POST)
	public String createSubject(@Valid @ModelAttribute("subject") Subject subject, BindingResult result,Model model) {
		if (result.hasErrors()) {
			setPageTitle("Create Subject");
			model.addAttribute("pageTitle",getPageTitle());
			return "admin/create";
		} else {
			subjectService.save(subject);
			return "redirect:/admin/details/" + subject.getId();
		}

	}

	@RequestMapping(value = { "/list","" }, method = RequestMethod.GET)
	public String listSubject(Model model) {
		List<Subject> list = subjectService.findAll();
		model.addAttribute("list", list);
		setPageTitle("List Subjects");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/list";
	}

	@RequestMapping(value = { "/details/{id}" }, method = RequestMethod.GET)
	public String subjectDetails(@PathVariable("id") Long id, Model model) {
		Subject subject = subjectService.findById(id);
		model.addAttribute("subject", subject);
		setPageTitle("Subject Details");
		model.addAttribute("pageTitle",getPageTitle());
		return "admin/details";
	}
	
	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public String editSubject(@PathVariable("id") Long id, Model model) throws NotFoundException {

		setPageTitle("Edit Subject");
		model.addAttribute("pageTitle",getPageTitle());
		Subject subject = subjectService.findById(id);
		if(subject == null)
		{
			throw new NotFoundException("Subject with id:"+id+" Not Found");
		}
		model.addAttribute("subject",subject);
		return "admin/edit";
	}
	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
	public String deleteSubject(@PathVariable("id") Long id, Model model) throws NotFoundException {

		Subject subject = subjectService.findById(id);
		if(subject == null)
		{
			throw new NotFoundException("Subject with id:"+id+" Not Found");
		}
		subjectService.delete(subject);
		return "redirect:/admin/list";
	}
	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	public String editSubject(@Valid @ModelAttribute("subject") Subject subject, BindingResult result,Model model) {
		if (result.hasErrors()) {
			setPageTitle("Edit Subject");
			model.addAttribute("pageTitle",getPageTitle());
			return "admin/edit";
		} else {
			subjectService.save(subject);
			return "redirect:/admin/details/" + subject.getId();
		}

	}

}
