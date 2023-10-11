package com.exam.response;

public class DeleteResponse {

	private String message;

	private Long id;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DeleteResponse(String message, Long id) {
		super();
		this.message = message;
		this.id = id;
	}

	public DeleteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
