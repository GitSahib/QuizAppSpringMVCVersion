package com.mems.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mems.domain.User;
import com.mems.domain.quiz.Student;
import com.mems.service.bl.interfaces.StudentService;
import com.mems.service.validator.impl.ProfileValidator;

@Controller
@RequestMapping(value="/admin")
public class StudentController  extends MEMSController {
	protected StudentController() {
		super("Student");
		// TODO Auto-generated constructor stub
	}

	Logger log = Logger.getLogger(getClass());
	@Autowired
	StudentService studentService;
	@Autowired
	ProfileValidator profileValidator;
	@RequestMapping(value = "/student/create", method = RequestMethod.GET)
	public String allList(Model model, HttpServletRequest request) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		model.addAttribute("myuser",userService.getLoggedInUser());
		return "admin/student/create";
	}

	@RequestMapping(value = "/student/list", method = RequestMethod.GET)
	public String studentsList(Model model) {
		return "admin/student/list";
	}
	
	@RequestMapping(value = "/student/assign", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> assignStudent(@RequestParam("userid") Long id) {

		User user = userService.findById(id);
		
		if (user != null) {
			Student student = studentService.assignStudent(id, userService.getLoggedInUser().getId());
			ResponseEntity<Student> response = new ResponseEntity<>(student,HttpStatus.OK);
			return response;
		} else {
			return new ResponseEntity<>("{\"Message\":\"Wrong Request\"}", HttpStatus.BAD_REQUEST);
		}
	} // method assign
	
	@RequestMapping(value = "/student/unAssign", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> unAssignStudent(@RequestParam("userid") Long id) {

			studentService.delete(id,userService.getLoggedInUser());
			ResponseEntity<String> response = new ResponseEntity<>("{\"Message\":\"Done\"}",HttpStatus.OK);
			return response;
	} // method assign

}
