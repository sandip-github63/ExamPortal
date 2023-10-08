package com.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	@Modifying
	@Query(value = "DELETE FROM quiz WHERE quiz_id = ?1 AND c_id = ?2", nativeQuery = true)
	public void deleteByQIdAndCId(Long qId, Long cId);

	@Query("SELECT q.category.cId FROM Quiz q WHERE q.quizId = :quizId")
	public Long findCategoryIdByQuizId(@Param("quizId") Long quizId);

}
