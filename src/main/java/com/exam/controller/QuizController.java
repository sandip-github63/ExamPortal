package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// Add quiz

	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
		this.quizService.addQuiz(quiz); // put cId null

		return ResponseEntity.ok(this.quizService.addQuiz(quiz));

	}

	// update Quiz

	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {

		return ResponseEntity.ok(quizService.updateQuiz(quiz));

	}

	@GetMapping("/")
	public ResponseEntity<?> getQuizzes() {

		return ResponseEntity.ok(this.quizService.getQuizzes());
	}

	@GetMapping("/{quizId}")
	public ResponseEntity<?> getQuizByQuizId(@PathVariable("quizId") Long quizId) {

		return ResponseEntity.ok(this.quizService.getQuiz(quizId));
	}

	@DeleteMapping("/{quizId}")
	public ResponseEntity<?> deleteQuiz(@PathVariable("quizId") Long quizId) {

		this.quizService.deleteQuiz(quizId);

		return ResponseEntity.ok("deleted :" + quizId);
	}

}
