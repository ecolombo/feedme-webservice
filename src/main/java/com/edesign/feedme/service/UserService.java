package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.UserRepository;
import com.edesign.feedme.dto.LoginRequestDto;
import com.edesign.feedme.entity.Admin;
import com.edesign.feedme.entity.User;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class UserService {
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	UserRepository userRepository;
	
	// Get all Users
	public Page<User> getUsers(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return userRepository.findAll(pageable);
	}	
	
	
	// Get one User by UserId
	public User getUser(int userId) {
		return userRepository.findById(userId).get();
	}
	
	// Add User
	public User addUser(User user) {
		System.out.println("[UserService.addUser] Trying to create user with name:"+user.getEmail());
		if(userRepository.existsByEmail(user.getEmail()))
			throw new BadRequestException("User with this email already exists.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	// Update User
	public User updateUser(User user) {
		if(user.getUserId() <=0 )
			throw new BadRequestException("UserId cannot be null or empty.");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(userRepository.existsById(user.getUserId()))
			return userRepository.save(user);
		else 
			throw new NotFoundException("User does not exist with provided userId.");

	}
	
	// Delete User
	public void deleteUser(int userId) {
		if(userId <=0 )
			throw new BadRequestException("userId id cannot be null or empty.");
		if(userRepository.existsById(userId))
			userRepository.deleteById(userId);
		else 
			throw new NotFoundException("The user does not exist with provided userId.");
	}
	
	// Validate Login
	public User validateLogin(LoginRequestDto loginDto) {
		
		boolean exist = userRepository.existsByEmail(loginDto.getEmail()) ;
		if(exist) {
			User user = userRepository.findByEmail(loginDto.getEmail());
			// passwordEncoder = new BCryptPasswordEncoder();
			if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
				return user;
			} else {
				throw new NotFoundException("Invalid password, password mismatch error.");
			}
		} else {
			throw new NotFoundException("User with this email adress does not exist.");
		}
	}	
	
	
}
