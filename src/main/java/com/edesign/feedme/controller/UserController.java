package com.edesign.feedme.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edesign.feedme.dto.ResponseDto;
import com.edesign.feedme.entity.User;
import com.edesign.feedme.service.UserService;

// CRUD operations for user

@RestController
public class UserController {

	@Autowired 
	UserService userService;
	
	/**
	 * Get all users
	 * @return
	 */
	@GetMapping("/users")
	public ResponseDto getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "userId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){		
		Page<User> paginatedResults = userService.getUsers(page, size, sort, sortOrder);
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Users found", new Date(), HttpStatus.OK.name(), paginatedResults); 	
		return response;
	}
	
	
	/**
	 * Get one user
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}")
	public ResponseDto getUser(@PathVariable int userId){
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("User found", new Date(), HttpStatus.OK.name(), userService.getUser(userId));
		return response; 
	}
	
	/**
	 * Add user
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public ResponseDto addUser(@RequestBody User user){
		// todo: add response when conflict occurs
		User resultUser = userService.addUser(user);
		ResponseDto response = new ResponseDto("User added", new Date(), HttpStatus.OK.name(), resultUser);
		return response;
	}
	
	/**
	 * Update user
	 * @param user
	 * @return
	 */
	@PutMapping("/users")
	public ResponseDto updateUser(@RequestBody User user){
		// todo: add response if update not possible
		User resultUser = userService.updateUser(user);
		ResponseDto response = new ResponseDto("User updated", new Date(), HttpStatus.OK.name(), resultUser);
		return response;
	}
	
	/**
	 * Delete user
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/users/{userId}")
	public ResponseDto updateUser(@PathVariable int userId){
		userService.deleteUser(userId);
		ResponseDto response = new ResponseDto("User is deleted sucessfully with userId : "+userId, new Date(),HttpStatus.OK.name(),null); 
		return response;
	}
}
