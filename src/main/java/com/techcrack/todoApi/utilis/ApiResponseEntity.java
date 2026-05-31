package com.techcrack.todoApi.utilis;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
	"success",
	"message",
	"localDateTime",
	"data"})
public class ApiResponseEntity<T> {
	private final boolean success;
	private final String message;
	private final T data;
	private final LocalDateTime localDateTime;
	
	public ApiResponseEntity(boolean success, String message, T data, LocalDateTime localDateTime) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
		this.localDateTime = localDateTime;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public static <T>ApiResponseEntity<T> success(String message, T data) {
		return new ApiResponseEntity<T>(
					true,
					message,
					data,
					LocalDateTime.now()
				);
	}
	
	public static <T>ApiResponseEntity<T> failure(String message) {
		return new ApiResponseEntity<T>(
					false,
					message, 
					null,
					LocalDateTime.now()
				);
				
	}
}
