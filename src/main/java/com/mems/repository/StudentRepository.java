package com.mems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mems.domain.User;
import com.mems.domain.quiz.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentAndTeacher(User findOne, User user);
}
