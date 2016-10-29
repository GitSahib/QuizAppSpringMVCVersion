package com.mems.service.bl.interfaces;

import com.mems.domain.User;
import com.mems.domain.quiz.Student;

public interface StudentService {
	Student findById(Long id);
	void save(Student student);
	Student assignStudent(Long studentId, Long teacherId);
	void delete(Long student_id, User user);
}
