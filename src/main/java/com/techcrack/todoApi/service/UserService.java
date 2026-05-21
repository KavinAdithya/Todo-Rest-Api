package com.techcrack.todoApi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techcrack.todoApi.entity.User;
import com.techcrack.todoApi.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username)
							.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		return org.springframework.security
                .core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
	}
}
