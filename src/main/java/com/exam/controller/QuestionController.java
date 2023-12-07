package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.exam.request.QuesToAnswer;
import com.exam.response.AnswerOfQuestionMessage;
import com.exam.response.DeleteResponse;
import com.exam.response.ResultOfQuiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {

		return ResponseEntity.ok(questionService.addQuestion(question));

	}

	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@RequestBody Question question) {

		return ResponseEntity.ok(this.questionService.updateQuestion(question));

	}

	@GetMapping("/quiz/{qId}")
	public ResponseEntity<?> getQuestionOfQuizWithLimit(@PathVariable("qId") Long qId) {

		Quiz quiz = this.quizService.getQuiz(qId);

		Set<Question> questions = quiz.getQuestions();

		List<Question> list = new ArrayList<>(questions);

		if (list.size() > quiz.getNumberOfQuestions()) {

			list = list.subList(0, quiz.getNumberOfQuestions());

		}

		Collections.shuffle(list);

		list.forEach(q -> {
			q.setAnswer(null);
		});

		return ResponseEntity.ok(list);

	}

	@GetMapping("/quiz/all/{qId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId) {

		Quiz q = new Quiz();
		q.setQuizId(qId);

		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(q);

		return ResponseEntity.ok(questionsOfQuiz);
	}

	@GetMapping("/{quesId}")
	public ResponseEntity<?> getQuestion(@PathVariable("quesId") Long quesId) {

		return ResponseEntity.ok(this.questionService.getQuestion(quesId));
	}

	@DeleteMapping("/{quesId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("quesId") Long quesId) {

		try {

			this.questionService.deleteQuestion(quesId);

			return ResponseEntity.ok(new DeleteResponse("Deleted successfully", quesId));

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");

		}

	}

	@PostMapping("/calculate-quiz")
	public ResponseEntity<?> calculateQuiz(@RequestBody List<Question> questions) {

		Integer marksPerQuestion = 0;
		Integer marksGot = 0;
		AtomicInteger correctAnswer = new AtomicInteger(0);
		AtomicInteger attempted = new AtomicInteger(0);

		try {
			marksPerQuestion = questions.get(0).getQuiz().getMaxMarks() / questions.size();

		} catch (NullPointerException e) {

			e.printStackTrace();

		}
		questions.forEach(q -> {
			System.out.println(q.getContent() + "  :" + q.getAnswer());

			if (this.questionService.getQuestion(q.getQuesId()).getAnswer().equals(q.getGivenAnswer())) {

				correctAnswer.incrementAndGet();

			}
			System.out.println("givenAnswer before condition : " + q.getGivenAnswer());

			if (q.getGivenAnswer() != null) {
				attempted.incrementAndGet();
			}

		});

		marksGot = correctAnswer.get() * marksPerQuestion;

		System.out.println("correctAnswer: " + correctAnswer + "  Attempted: " + attempted);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResultOfQuiz(marksGot, correctAnswer.get(), attempted.get()));

	}

	@PostMapping("/get-answer")
	public ResponseEntity<?> getAnswerByQuestions(@RequestBody QuesToAnswer quesToAnswer) {

		Map<Long, String> answer = this.questionService.getAnswerOfQuestions(quesToAnswer);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new AnswerOfQuestionMessage("Got the Answer of questions", answer));
	}

}
