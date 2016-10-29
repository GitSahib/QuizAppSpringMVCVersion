package com.mems.service.bl.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mems.domain.User;
import com.mems.domain.quiz.Student;
import com.mems.repository.StudentRepository;
import com.mems.repository.UserRepository;
import com.mems.service.bl.interfaces.StudentService;
@PreAuthorize("hasRole('Teacher')")
@Transactional
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentDao;
    @Autowired
    private UserRepository userDao;
		
	@Override
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return studentDao.findOne(id);
	}

	@Override
	public void save(Student student) {
		// TODO Auto-generated method stub
		studentDao.save(student);
	}

	@Override
	public void delete(Long student_id,User user) {
		// TODO Auto-generated method stub
		Student student = studentDao.findByStudentAndTeacher(userDao.findOne(student_id),user);
		studentDao.delete(student);
	}
	
	@Override
	public Student assignStudent(Long studentId, Long teacherId)
	{
		Student student = new Student();
		return student;
	}

	

	
	
	

} // The End of Class;