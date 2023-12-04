package com.exam.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.repository.QuestionRepository;
import com.exam.request.QuesToAnswer;
import com.exam.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);

	}

	@Override
	public Question updateQuestion(Question question) {
		Quiz quiz = this.questionRepository.findById(question.getQuesId()).get().getQuiz();
		question.setQuiz(quiz);

		return this.questionRepository.save(question);

	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long questionId) {
		Question d = new Question();
		d.setQuesId(questionId);

		this.questionRepository.delete(d);

	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public Map<Long, String> getAnswerOfQuestions(QuesToAnswer ques) {
		List<Long> quesId = ques.getQuesId();

		Map<Long, String> answer = new HashMap<Long, String>();

		for (Long q : quesId) {
			Optional<Question> question = this.questionRepository.findById(q);
			if (question.isPresent()) {
				answer.put(q, question.get().getAnswer());
			}
		}

		return answer;
	}

}
