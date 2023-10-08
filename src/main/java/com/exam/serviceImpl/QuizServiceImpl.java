package com.exam.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.repository.QuizRepository;
import com.exam.service.QuizService;

import jakarta.transaction.Transactional;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	QuizRepository quizRepository;

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

		Category category = this.quizRepository.findById(quiz.getQuizId()).get().getCategory();

		quiz.setCategory(category);

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

		Long cId = this.quizRepository.findCategoryIdByQuizId(quizId);

		System.out.println("category id....." + cId);

		this.quizRepository.deleteByQIdAndCId(quizId, cId);

	}

}
