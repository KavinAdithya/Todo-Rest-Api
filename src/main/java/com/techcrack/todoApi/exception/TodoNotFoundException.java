package com.techcrack.todoApi.exception;

public class TodoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1409276161500958532L;
	
	public TodoNotFoundException(String message) {
		super(message);
	}
}
