package com.techcrack.todoApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techcrack.todoApi.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	@Query("SELECT t FROM Todo t WHERE t.completed = true")
	List<Todo> findPendingTodos();
	Optional<Todo> findByIdAndUserUsername(Long id, String username);
	
	List<Todo> findByUserUsername(String username);
}
