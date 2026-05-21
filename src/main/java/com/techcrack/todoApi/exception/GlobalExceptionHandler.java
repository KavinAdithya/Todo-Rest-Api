package com.techcrack.todoApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techcrack.todoApi.service.utilis.ApiResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleTodoNotFoundException(TodoNotFoundException ex) {
		return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(ApiResponseEntity
							.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleUserNotFoundException(UserNotFoundException ex) {
		return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(ApiResponseEntity
							.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleUnauthorizedAcessException(UnauthorizedAccessException ex) {
		return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(ApiResponseEntity
							.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleAccessDeniedExceptio(AccessDeniedException ex) {
		return ResponseEntity
					.status(HttpStatus.FORBIDDEN)
					.body(ApiResponseEntity
							.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleGlobalException(Exception ex) {
		return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ApiResponseEntity
							.failure("Something went wrong ! " + ex.getMessage()));
	}
}
