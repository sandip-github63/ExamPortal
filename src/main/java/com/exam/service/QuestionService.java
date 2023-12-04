package com.exam.service;

import java.util.Map;
import java.util.Set;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.request.QuesToAnswer;

public interface QuestionService {

	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getQuestions();

	public Question getQuestion(Long questionId);

	public void deleteQuestion(Long questionId);

	public Set<Question> getQuestionsOfQuiz(Quiz quiz);

	public Map<Long, String> getAnswerOfQuestions(QuesToAnswer quesToAnswer);

}
