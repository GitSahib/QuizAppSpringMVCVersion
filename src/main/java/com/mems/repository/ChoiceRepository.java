package com.mems.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mems.domain.quiz.Choice;
import com.mems.domain.quiz.Question;
@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long>{
	Choice findById(Long Id);
	void delete(Choice Choice);
	List<Choice> findByQuestion(Question question);
}
