package com.exam.request;

import java.util.List;
import java.util.Map;

public class QuesToAnswer {
	
	private List<Long> quesId;
	
	private Map<Long, String>  answer;

	public List<Long> getQuesId() {
		return quesId;
	}

	public void setQuesId(List<Long> quesId) {
		this.quesId = quesId;
	}

	public Map<Long, String> getAnswer() {
		return answer;
	}

	public void setAnswer(Map<Long, String> answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "QuesToAnswer [quesId=" + quesId + ", answer=" + answer + ", getQuesId()=" + getQuesId()
				+ ", getAnswer()=" + getAnswer() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	

}
