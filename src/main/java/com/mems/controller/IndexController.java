package com.mems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mems.domain.Contact;
import com.mems.service.bl.interfaces.ContactService;
import com.mems.service.validator.impl.ContactValidator;

@Controller
public class IndexController extends MEMSController {
	
	protected IndexController() {
		super("Home");
	}

	@Autowired
	ContactService contactService;
	@Autowired
	ContactValidator contactValidator;
    
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(Model model) {    	
    	return "index";
    }
    
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index(Model model) {    	
    	return "redirect:/";
    }
    
    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String about(Model model) {
          return "about";
    }
    
    @RequestMapping(value = {"/maintenance"}, method = RequestMethod.GET)
    public String maintenance(Model model) {
          return "maintenance";
    }
    
    @RequestMapping(value = {"/error-custom"}, method = RequestMethod.GET)
    public String error(Model model) {
          return "error";
    }
    
    @RequestMapping(value = {"/contact", "/contact.html","contact.jsp"}, method = RequestMethod.GET)
    public String contact(@ModelAttribute("contactForm") Contact contactForm) {
    	return "contact";
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String register(@ModelAttribute("contactForm") Contact contactForm, 
			BindingResult bindingResult, Model model) {
    	
    	contactValidator.validate(contactForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "contact";
		}
    	contactService.save(contactForm);
    	model.addAttribute("message","Thank you for contacting we will soon contact you");
    	return "contact";
    	
    }
   
    @RequestMapping(value="/*",method=RequestMethod.GET)
    public String error_404(Model model)
    {
    	model.addAttribute("title","404");
    	return "404";
    }
}
