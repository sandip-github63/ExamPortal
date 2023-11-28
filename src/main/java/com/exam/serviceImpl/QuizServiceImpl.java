package com.exam.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.repository.CategoryRepository;
import com.exam.repository.QuizRepository;
import com.exam.service.QuizService;

import jakarta.transaction.Transactional;

@Service
public class QuizServiceImpl implements QuizService {

	private static final Logger logger = LogManager.getLogger(QuizServiceImpl.class);

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		try {
			Optional<Category> data = this.categoryRepository.findById(quiz.getCategory().getcId());

			if (data.isPresent()) {
				quiz.setCategory(data.get());
			}

		} catch (Exception e) {
			System.out.println("Kinldy pass cId also.....");

		}

		return this.quizRepository.save(quiz);

	}

	@Override
	public Set<Quiz> getQuizzes() {

		this.logger.info("Get all Quizzes run....");

		return new HashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return this.quizRepository.findById(quizId).get();

	}

	@Override
	@Transactional
	public void deleteQuiz(Long quizId) {

		this.quizRepository.deleteById(quizId);

	}

	@Override
	public List<Quiz> getQuzzesOfCategory(Category c) {
		return this.quizRepository.findBycategory(c);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {

		return this.quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizOfCategory(Category c) {

		return this.quizRepository.findByCategoryAndActive(c, true);
	}

}
