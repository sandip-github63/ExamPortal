package com.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.model.Category;
import com.exam.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	public List<Quiz> findBycategory(Category c);

	public List<Quiz> findByActive(boolean status);

	public List<Quiz> findByCategoryAndActive(Category c, Boolean status);

}
