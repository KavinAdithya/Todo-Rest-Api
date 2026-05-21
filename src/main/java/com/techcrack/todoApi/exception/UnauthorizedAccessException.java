package com.techcrack.todoApi.exception;

public class UnauthorizedAccessException extends RuntimeException {

	private static final long serialVersionUID = 580289812837464855L;
	
	public UnauthorizedAccessException(String message) {
		super(message);
	}

}
