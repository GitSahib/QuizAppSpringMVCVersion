package com.mems.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mems.domain.Profile;
import com.mems.domain.User;
import com.mems.service.security.interfaces.ProfileService;
import com.mems.service.validator.impl.ProfileValidator;

@Controller
public class ProfileController  extends MEMSController {
	protected ProfileController() {
		super("Profile");
		// TODO Auto-generated constructor stub
	}

	Logger log = Logger.getLogger(getClass());
	@Autowired
	ProfileService profileService;
	@Autowired
	ProfileValidator profileValidator;
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profileinfo(Model model, HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("profileForm", user.getProfile());
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profileinfo(@ModelAttribute("profileForm") Profile profileForm, BindingResult bindingResult,
			Model model, RedirectAttributes rda) {
		profileValidator.validate(profileForm, bindingResult);
		if (bindingResult.hasErrors()) {
			log.error("User Profile Form Invalid");
			return "profile";
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findByUsername(userDetails.getUsername());
		if (!profileForm.getUser().getPassword().isEmpty()) {
			userService.changePassword(user.getUsername(), profileForm.getUser().getPassword());
			rda.addFlashAttribute("passwordChanged", true);
		}
		profileService.updateUserProfile(user, profileForm);

		return "redirect:/profile";
	}

	@RequestMapping(value = "/colors", method = RequestMethod.GET)
	public String colors(@RequestParam(value = "colors", defaultValue = "gray") String[] colors, Model model) {

		model.addAttribute("list", colors);
		return "colors";
	}

	@RequestMapping(value = "profile/uploadPhoto", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadphoto") MultipartFile uploadfile) {

		User user = userService.getLoggedInUser();
		String contentType = uploadfile.getContentType();
		String[] allowedTypes = { "image/png", "image/jpg", "image/gif", "image/jpeg" };
		if (Arrays.asList(allowedTypes).contains(contentType)) {
			ResponseEntity<String> response = profileService.saveImage(uploadfile, user);
			return response;
		} else {
			return new ResponseEntity<>("{\"Message\":\"Wrong File Type\"}", HttpStatus.BAD_REQUEST);
		}
	} // method uploadFile

}
