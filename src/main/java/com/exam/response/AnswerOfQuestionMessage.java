package com.exam.response;

import java.util.Map;

public class AnswerOfQuestionMessage {

	private String message;

	private Map<Long, String> answer;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<Long, String> getAnswer() {
		return answer;
	}

	public void setAnswer(Map<Long, String> answer) {
		this.answer = answer;
	}

	public AnswerOfQuestionMessage(String message, Map<Long, String> answer) {
		super();
		this.message = message;
		this.answer = answer;
	}

	public AnswerOfQuestionMessage() {
		super();

	}

}
