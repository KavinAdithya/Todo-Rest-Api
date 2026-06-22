package com.techcrack.todoApi.startupLoader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.techcrack.todoApi.model.Todo;
import com.techcrack.todoApi.model.User;
import com.techcrack.todoApi.service.TodoService;
import com.techcrack.todoApi.service.UserService;

@Component
public class 	BootUsersAndTodos implements CommandLineRunner {
	
	private final UserService userService;
	private final TodoService todoService;
	
	public BootUsersAndTodos(UserService userService, TodoService todoService) {
		this.userService = userService;
		this.todoService = todoService;
	}

	@Override
	public void run(String... args) throws Exception {
		List<Todo> todos = new ArrayList<>();

		Todo todo1 = new Todo();
		todo1.setTodoTitle("Complete Growth Engine Module");
		todo1.setDescription("Growth Engine should be completed to move ahead");
		todo1.setCompleted(false);
		todo1.setDueDate(LocalDate.now().plusDays(1));

		Todo todo2 = new Todo();
		todo2.setTodoTitle("Learn Spring Security");
		todo2.setDescription("Understand JWT authentication and authorization");
		todo2.setCompleted(false);
		todo2.setDueDate(LocalDate.now().plusDays(2));

		Todo todo3 = new Todo();
		todo3.setTodoTitle("Practice LeetCode Problems");
		todo3.setDescription("Solve 3 medium DSA problems today");
		todo3.setCompleted(true);
		todo3.setDueDate(LocalDate.now().plusDays(3));


		Todo todo4 = new Todo();
		todo4.setTodoTitle("Prepare DTO Layer");
		todo4.setDescription("Implement request and response DTOs properly");
		todo4.setCompleted(false);
		todo4.setDueDate(LocalDate.now().plusDays(4));


		Todo todo5 = new Todo();
		todo5.setTodoTitle("Hibernate Revision");
		todo5.setDescription("Revise dirty checking and persistence context");
		todo5.setCompleted(false);
		todo5.setDueDate(LocalDate.now().plusDays(5));


		todos.add(todo1);
		todos.add(todo2);
		todos.add(todo3);
		todos.add(todo4);
		todos.add(todo5);

		User user = new User();

		user.setUsername("dummy");
		user.setPassword("Techcrack@3");

		userService.createUser(user);

		for (Todo todo : todos) {
		    todo.setUser(user);
		    todoService.createTodo(user.getUsername(), todo);
		}
			
	}

}
