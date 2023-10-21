package com.exam.response;

public class ResultOfQuiz {

	private Integer marksGot;

	private Integer correctAnswer;

	private Integer attempted;

	public Integer getMarksGot() {
		return marksGot;
	}

	public void setMarksGot(Integer marksGot) {
		this.marksGot = marksGot;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Integer getAttempted() {
		return attempted;
	}

	public void setAttempted(Integer attempted) {
		this.attempted = attempted;
	}

	public ResultOfQuiz(Integer marksGot, Integer correctAnswer, Integer attempted) {
		super();
		this.marksGot = marksGot;
		this.correctAnswer = correctAnswer;
		this.attempted = attempted;
	}

	public ResultOfQuiz() {
		super();
		// TODO Auto-generated constructor stub
	}

}
