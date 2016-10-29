package com.mems.repository;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mems.domain.quiz.Quiz;
@Repository
public interface QuizeRepository extends JpaRepository<Quiz, Long>{
	Quiz findByTitle(String title);
	Quiz findByDescription(String description);
	Quiz findByStartTime(Date date);
	Quiz findByEndTime(Date date);
}
