package com.techcrack.todoApi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techcrack.todoApi.entity.Todo;
import com.techcrack.todoApi.entity.User;
import com.techcrack.todoApi.exception.TodoNotFoundException;
import com.techcrack.todoApi.repository.TodoRepository;


@Service
public class TodoService {
	private final TodoRepository todoRepo;
	private final UserService userService;
	
	public TodoService(TodoRepository todoRepo, UserService userService) {
		this.todoRepo = todoRepo;
		this.userService = userService;
	}
	
	@Transactional
	public Todo createTodo(String username, Todo todo) {
		User user = userService.getUserByUsername(username);
		
		todo.setUser(user);
		
		return todoRepo.save(todo);
	}
	
	public List<Todo> getAllTodosByUsername(String username) {
		return todoRepo.findByUserUsername(username);
	}
	
	public Todo getTodoByIdAndUsername(String username, Long id) {
		return 	todoRepo.findByIdAndUserUsername(id, username)
					.orElseThrow(() -> new TodoNotFoundException("User doesn't have todo with id : " + id));
	}
	
	@Transactional
	public Todo updateTodo(Todo todo, String username) {
		return copyTodoAndUpdate(todo, username);
	}
	
	private Todo copyTodoAndUpdate(Todo todo, String username) {
		Todo todoFromRepo = todoRepo.findByIdAndUserUsername(todo.getId(), username)
									.orElseThrow(
											() -> new TodoNotFoundException("Todo not found with id : " + todo.getId())
									);
		
		copyTodo(todo, todoFromRepo);
		
		return todoFromRepo;
	}
	
	
	private void copyTodo(Todo src, Todo des) {
		des.setTodoTitle(src.getTodoTitle());
		des.setDescription(src.getDescription());
		des.setDueDate(src.getDueDate());
		des.setCompleted(src.isCompleted());
	}
	
	@Transactional
	public void removeTodoById(String username, Long id) {
		Todo todo = getTodoByIdAndUsername(username, id);

		todoRepo.delete(todo);
		
	}
}
