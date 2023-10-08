package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {

		return ResponseEntity.ok(questionService.addQuestion(question));

	}

	// update the question

	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@RequestBody Question question) {

		return ResponseEntity.ok(this.questionService.updateQuestion(question));

	}

	// get all question of any quid

	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId) {

		// how much question you want to get ? ans->get question according to quiz's
		// numberOfQuestions

		Quiz quiz = this.quizService.getQuiz(qId);

		Set<Question> questions = quiz.getQuestions();

		List list = new ArrayList(questions);

		if (list.size() > quiz.getNumberOfQuestions()) {

			list = list.subList(0, quiz.getNumberOfQuestions() + 1);

		}

		Collections.shuffle(list);

		return ResponseEntity.ok(list);

	}

	// get single question

	@GetMapping("/{quesId}")
	public ResponseEntity<?> getQuestion(@PathVariable("quesId") Long quesId) {

		return ResponseEntity.ok(this.questionService.getQuestion(quesId));
	}

	// delete question by questionId

	@DeleteMapping("/{quesId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("quesId") Long quesId) {

		try {

			this.questionService.deleteQuestion(quesId);
			return ResponseEntity.ok("deleted " + quesId);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");

		}

	}

}
