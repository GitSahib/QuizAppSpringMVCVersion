package com.mems.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mems.domain.quiz.Question;
import com.mems.domain.quiz.QuestionType;
import com.mems.domain.quiz.Subject;
/**
 * @author sahib
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	@Query("From Question q where q.text like %:word%")
	List<Question> searchByKeyword(@Param("word")String word);
	List<Question> findByTextIgnoreCaseContaining(String word);
	Question findById(Long Id);
	List<Question> findBySubject(Subject subject);
	void delete(Question question);
	List<Question> findByType(QuestionType type);
	@Query("From Question q Where q.subject=?1 And q.type=?2")
	List<Question> findRandomQuestionBySubejctAndType(Subject subject,
			QuestionType type);
}
