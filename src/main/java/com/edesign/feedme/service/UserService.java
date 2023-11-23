package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.UserRepository;
import com.edesign.feedme.entity.User;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	// Get all Useres
	public Page<User> getUseres(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return userRepository.findAll(pageable);
	}	
	
	
	// Get one User by UserId
	public User getUser(int categoryId) {
		return userRepository.findById(categoryId).get();
	}
	
	// Add User
	public User addUser(User user) {
		System.out.println("[UserService.addUser] Trying to create user with name:"+user.getEmail());
		if(userRepository.existsByEmail(user.getEmail()))
			throw new BadRequestException("User with this email already exists.");
		return userRepository.save(user);
	}
	
	// Update User
	public User updateCategory(User user) {
		if(user.getUserId() <=0 )
			throw new BadRequestException("UserId cannot be null or empty.");
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
	
	
}
