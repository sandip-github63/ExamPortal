package com.exam.controller;

import java.util.List;

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

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.response.DeleteResponse;
import com.exam.service.QuizService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
		this.quizService.addQuiz(quiz); // put cId null

		return ResponseEntity.ok(this.quizService.addQuiz(quiz));

	}

	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz) {

		System.out.println(quiz);

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

		return ResponseEntity.ok(new DeleteResponse("Deleted successfully", quizId));
	}

	@GetMapping("/category/{cId}")
	public ResponseEntity<?> getQuzzesOfCategory(@PathVariable("cId") Long cId) {

		Category c = new Category();
		c.setcId(cId);

		return ResponseEntity.ok(this.quizService.getQuzzesOfCategory(c));

	}

	@GetMapping("/active")
	public ResponseEntity<?> getActiveQuizzes() {
		List<Quiz> activeQuizzes = this.quizService.getActiveQuizzes();

		return ResponseEntity.ok(activeQuizzes);

	}

	@GetMapping("category/active/{cId}")
	public ResponseEntity<?> getActiveQuizzesOfCategory(@PathVariable("cId") Long cId) {
		Category c = new Category();
		c.setcId(cId);

		List<Quiz> activeQuizOfCategory = this.quizService.getActiveQuizOfCategory(c);
		return ResponseEntity.ok(activeQuizOfCategory);

	}

}
