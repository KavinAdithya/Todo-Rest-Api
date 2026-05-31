package com.techcrack.todoApi.utilis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techcrack.todoApi.dtos.CreateTodoRequestDTO;
import com.techcrack.todoApi.dtos.TodoResponseDTO;
import com.techcrack.todoApi.dtos.UpdateTodoRequestDTO;
import com.techcrack.todoApi.model.Todo;

@Component
public class TodoHelper {
	public Todo mapRequestToTodo(CreateTodoRequestDTO requestTodo) {
		Todo todo = new Todo();
		
		todo.setDescription(requestTodo.getDescription());
		todo.setCompleted(requestTodo.isCompleted());
		todo.setTodoTitle(requestTodo.getTodoTitle());
		todo.setDueDate(requestTodo.getDueDate());
		
		return todo;
	}
	
	public Todo mapRequestToTodo(UpdateTodoRequestDTO requestTodo) {
		Todo todo = new Todo();
		
		todo.setId(requestTodo.getId());
		todo.setDescription(requestTodo.getDescription());
		todo.setCompleted(requestTodo.isCompleted());
		todo.setTodoTitle(requestTodo.getTodoTitle());
		todo.setDueDate(requestTodo.getDueDate());
		
		return todo;
	}
	
	public TodoResponseDTO createTodoResponse(Todo todo) {
		TodoResponseDTO response = new TodoResponseDTO();
		
		response.setId(todo.getId());
		response.setDescription(todo.getDescription());
		response.setDueDate(todo.getDueDate());
		response.setTodoTitle(todo.getTodoTitle());
		response.setCompleted(todo.isCompleted());
		response.setUsername(todo.getUser().getUsername());
		
		return response;
	}
	
	
	public List<TodoResponseDTO> createTodosResponse(List<Todo> todos) {
		List<TodoResponseDTO> responseTodos  = new ArrayList<>();
		
		for (Todo todo : todos) {
			responseTodos.add(createTodoResponse(todo));
		}
		
		return responseTodos;
	}
}
