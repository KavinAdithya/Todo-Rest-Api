package com.techcrack.todoApi.dtos;

import java.time.LocalDate;


public class CreateTodoRequestDTO {
	
	private String title;

	private String description;

	private LocalDate dueDate;

	private boolean completed;
	
	public CreateTodoRequestDTO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	
	@Override
	public String toString() {
		return "CreateTodoRequestDTO [todoTitle=" + title + ", description=" + description + ", dueDate=" + dueDate
				+ ", completed=" + completed + "]";
	}
	
}
