package com.exam.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub

		// if request contain cId as null then it will update cId as null in table hence
		// integrity remove

		// Category category =
		// this.quizRepository.findById(quiz.getCategory().getcId()).get().getCategory();

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
		// TODO Auto-generated method stub
		return new HashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		// TODO Auto-generated method stub
		return this.quizRepository.findById(quizId).get();

	}

	@Override
	@Transactional
	public void deleteQuiz(Long quizId) {

		this.quizRepository.deleteById(quizId);

	}

	@Override
	public List<Quiz> getQuzzesOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizRepository.findBycategory(c);
	}

}
