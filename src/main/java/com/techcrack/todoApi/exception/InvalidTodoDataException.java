package com.techcrack.todoApi.exception;

public class InvalidTodoDataException extends RuntimeException {
    public InvalidTodoDataException(String message) {
        super(message);
    }
}
