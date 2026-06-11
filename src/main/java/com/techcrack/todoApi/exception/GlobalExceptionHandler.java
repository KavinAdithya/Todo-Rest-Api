package com.techcrack.todoApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techcrack.todoApi.utilis.ApiResponseEntity;

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
	public ResponseEntity<ApiResponseEntity<Object>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
		return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(ApiResponseEntity
							.failure(ex.getMessage()));
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleBadCredentialException(BadCredentialsException ex) {
		ex.printStackTrace();
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(ApiResponseEntity
						.failure(ex.getMessage()  + "Testing"));
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleAccessDeniedException(AccessDeniedException ex) {
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

	@ExceptionHandler(InvalidTodoDataException.class)
	public ResponseEntity<ApiResponseEntity<Object>> handleInvalidTodoDataException(InvalidTodoDataException ex) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponseEntity
						.failure("Invalid Todo Data ! " + ex.getMessage()));
	}
}
