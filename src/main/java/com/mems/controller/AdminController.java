package com.mems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mems.service.bl.interfaces.QuestionService;
import com.mems.service.bl.interfaces.SubjectService;

@Controller
@RequestMapping("/admin")
public class AdminController extends MEMSController {
	@Autowired 
	QuestionService questionService;
	@Autowired 
	SubjectService subjectService;
	protected AdminController() {
		super("Backend");
	}
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String home(Model model) {
		setPageTitle("Admin Home");
    	return "admin/index";
    }
	

}
