package com.edesign.feedme.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edesign.feedme.service.AdminService;
import com.edesign.feedme.service.UserService;
import com.edesign.feedme.dto.LoginRequestDto;
import com.edesign.feedme.dto.ResponseDto;
import com.edesign.feedme.dto.AuthResponseDto;
import com.edesign.feedme.entity.Admin;
import com.edesign.feedme.entity.User;


@RestController
public class AuthController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;
	
	//@CrossOrigin(origins="*", maxAge=3600) 
	@PostMapping("/admins/login")
	public ResponseDto adminLogin(@RequestBody LoginRequestDto login) {
		Admin admin = adminService.validateLogin(login);
		AuthResponseDto authResponseDto = new AuthResponseDto();
		authResponseDto.setId(admin.getAdminId());
		authResponseDto.setAuthToken(admin.getAuthToken());
		authResponseDto.setFullName(admin.getFullName());
		return new ResponseDto("Admin user loggedIn sucessfully.", new Date(),HttpStatus.OK.name(),authResponseDto);
	}
	
	
	@PostMapping("/users/login")
	public ResponseDto userLogin(@RequestBody LoginRequestDto login) {
		User user= userService.validateLogin(login);
		AuthResponseDto authResponseDto = new AuthResponseDto();
		authResponseDto.setId(user.getUserId());
		authResponseDto.setAuthToken(user.getAuthToken());
		authResponseDto.setFullName(user.getFullName());
		return new ResponseDto("Admin user loggedIn sucessfully.", new Date(),HttpStatus.OK.name(),authResponseDto);
	}
	
}

