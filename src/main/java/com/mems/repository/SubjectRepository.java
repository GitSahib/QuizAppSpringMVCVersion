package com.mems.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mems.domain.quiz.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
	@Query("From Subject s where s.name like %:word%")
	List<Subject> searchByKeyword(@Param("word")String word);
	Subject findById(Long Id);
	void delete(Subject Subject);
	List<Subject> findByMission(String mission);
}
