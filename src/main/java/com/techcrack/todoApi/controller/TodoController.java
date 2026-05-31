package com.techcrack.todoApi.controller;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techcrack.todoApi.dtos.CreateTodoRequestDTO;
import com.techcrack.todoApi.dtos.TodoResponseDTO;
import com.techcrack.todoApi.dtos.UpdateTodoRequestDTO;
import com.techcrack.todoApi.model.Todo;
import com.techcrack.todoApi.service.TodoService;
import com.techcrack.todoApi.utilis.ApiResponseEntity;
import com.techcrack.todoApi.utilis.TodoHelper;

@RestController
@RequestMapping("/users")
public class TodoController {
	private final TodoService service;
	private final TodoHelper helper;
	private final Logger logger;

	public TodoController(TodoService service, TodoHelper helper) {
		this.service = service;
		this.helper = helper;
		this.logger = LoggerFactory.getLogger(getClass());
	}
	
	@GetMapping("/{username}/todos")
	public ResponseEntity<ApiResponseEntity<List<TodoResponseDTO>>> getTodos(
			@PathVariable("username") String username, 
			@AuthenticationPrincipal UserDetails user) {
		
		if (!user.getUsername().equals(username)) {
			throw new AccessDeniedException("Access Denied");
		}
		
		logger.info("Retrieve Todos Request received to get todos for username : [ {} ]", username);
		
		List<Todo> todos = service.getAllTodosByUsername(username);
		
		logger.info("Retrieve Todos Request processed. Todos found {} username  : [ {} ]" , todos.size(), username);
		
		List<TodoResponseDTO> todoResponse = helper.createTodosResponse(todos);
	
		return ResponseEntity.ok(
				ApiResponseEntity
					.success("Todos Fetched Successfully", todoResponse)
				);
	}
	
	@PostMapping("/{username}/todos")
	public ResponseEntity<ApiResponseEntity<TodoResponseDTO>> createTodo(
					@PathVariable("username") String username, 
					@RequestBody CreateTodoRequestDTO todoDTO, 
					@AuthenticationPrincipal UserDetails user) {
		
		if (!user.getUsername().equals(username)) {
			throw new AccessDeniedException("Access Denied");
		}
		
		logger.info("Create Todo process started for username : [ {} ]", username);
		
		logger.info(todoDTO.toString());
		
		Todo todo = helper.mapRequestToTodo(todoDTO);
		
		TodoResponseDTO todoInMemory =  helper.createTodoResponse(
					service.createTodo(username, todo)
				);
		
		logger.info("Create Todo process completed successfully username : [ {} ]", username);
		
		return ResponseEntity.ok(
				ApiResponseEntity
					.success("Todo Created Successfully", todoInMemory)
				);
	}
	
	@GetMapping("/{username}/todos/{id}") 
	public ResponseEntity<ApiResponseEntity<TodoResponseDTO>> getTodo(
			@PathVariable("username") String username, 
			@PathVariable("id") Long id,
			@AuthenticationPrincipal UserDetails user) {
		
		if (!user.getUsername().equals(username)) {
			throw new AccessDeniedException("Access Denied");
		}
		
		logger.info("Retrieve Todo Request received to get todo id {} for username : [ {} ]", id, username);
		
		Todo todo = service.getTodoByIdAndUsername(username, id);
		
		logger.info("Retrirve Todo Request completed to get todo id {} for username : [ {} ]", (todo != null ? todo.getId() : -1), username);
		
		TodoResponseDTO responseTodo =  helper.createTodoResponse(todo);
		
		return ResponseEntity.ok(
				ApiResponseEntity
					.success("Todo Fetched Successfully", responseTodo)
				);
	}
	
	@PutMapping("/{username}/todos")
	public ResponseEntity<ApiResponseEntity<TodoResponseDTO>> updateTodo(
			@PathVariable("username") String username, 
			@RequestBody UpdateTodoRequestDTO todoDTO,
			@AuthenticationPrincipal UserDetails user) {
		
		if (!user.getUsername().equals(username)) {
			throw new AccessDeniedException("Access Denied");
		}
		
		logger.info("Update Request received to update todo Id {} for username : [ {} ]", todoDTO.getId(),  username);
		Todo todo  = helper.mapRequestToTodo(todoDTO);
		
		TodoResponseDTO response =  helper.createTodoResponse(
					service.updateTodo(todo, username)
				);
		
		logger.info("Update Request completed for username : [ {} ]", username);
		
		return ResponseEntity.ok(
				ApiResponseEntity
					.success("Todo updated Successfully", response)
				);
	}
	
	@DeleteMapping("/{username}/todos/{id}")
	public ResponseEntity<ApiResponseEntity<Object>> removeTodo(
			@PathVariable("username") String username, 
			@PathVariable("id") Long id,
			@AuthenticationPrincipal UserDetails user) {
		
		if (!user.getUsername().equals(username)) {
			throw new AccessDeniedException("Access Denied");
		}
		
		logger.info("Remove Todo Request received to remove todo id {} for username : [ {} ]", id, username);
		
		service.removeTodoById(username, id);
		
		logger.info("Remove Todo Request completed removed todo id {} for username : [ {} ]", id, username);
		return ResponseEntity.ok(
				ApiResponseEntity
					.success("Todo removed Successfully", null)
				);
	}
}
