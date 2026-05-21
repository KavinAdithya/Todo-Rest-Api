package com.techcrack.todoApi.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4873740916389044430L;
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
