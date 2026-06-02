package com.techcrack.todoApi.service;

import com.techcrack.todoApi.jwtConfig.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.techcrack.todoApi.model.User;
import com.techcrack.todoApi.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authManager;
	private final JwtService jwtService;

	public UserService(UserRepository userRepo, AuthenticationManager authManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepo = userRepo;
		this.authManager = authManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("user doesn't exists"));
	}
	
	public User updateUser(User user) {
		return createUser(user);
	}
	
	public User createUser(User user) {
		user.setPassword(
					passwordEncoder.encode(user.getPassword())
				);
		return userRepo.save(user);
	}

	public String verify(User user) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated())
			return jwtService.generateToken(user.getUsername());

		return "Failed";
	}


}
