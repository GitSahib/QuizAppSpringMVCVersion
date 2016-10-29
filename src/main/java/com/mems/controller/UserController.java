package com.mems.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mems.domain.Profile;
import com.mems.domain.Role;
import com.mems.domain.User;
import com.mems.service.security.interfaces.ProfileService;
import com.mems.service.security.interfaces.RoleService;
import com.mems.service.security.interfaces.SecurityService;
import com.mems.service.validator.impl.UserValidator;

@SessionAttributes("user")
@Controller
public class UserController  extends MEMSController  {

	protected UserController() {
		super("User");
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private ProfileService profileService;
	@Autowired
	private RoleService roleService;
	private Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("userForm") User userForm, BindingResult errors,String error, String logout) {
		
		if (error != null)
			errors.rejectValue("password", "errors.username.or.password.is.invalid");			
		if (logout != null)
			errors.rejectValue("password", "message.you.are.loggedout.successfully");
		else 
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Object user = auth !=null ? auth.getPrincipal():null;
			if(user instanceof UserDetails)
			{
				return "redirect:/profile";
			}
		}
		return "login";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registration() {
		return "redirect:login#toregister";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,RedirectAttributes rda) {

		userValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			log.error("User Form Invalid");
			model.addAttribute("register","yes");
			return "login";
		}
		Role role = roleService.findRoleByName("Student");
		Set<Role> roles = new HashSet<Role>();		
		roles.add(role);
		userForm.setRoles(roles);	
		Profile profile = userForm.getProfile();
		userForm.setProfile(null);
		userService.save(userForm);
		
		
		profile.setUser(userForm);
		profileService.save(profile);		
		securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
		return "redirect:/index";
	}

	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/index";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessdenied() {
		return "error/accessDenied";
	}

	

	

	
}
