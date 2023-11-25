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
import com.edesign.feedme.entity.Admin;
import com.edesign.feedme.service.AdminService;

// CRUD operations for admin

@RestController
public class AdminController {

	@Autowired 
	AdminService adminService;
	
	/**
	 * Get all admins
	 * @return
	 */
	@GetMapping("/admins")
	public ResponseDto getAdmins(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "adminId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){		
		Page<Admin> paginatedResults = adminService.getAdmins(page, size, sort, sortOrder);
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Admins found", new Date(), HttpStatus.OK.name(), paginatedResults); 	
		return response;
	}
	
	
	/**
	 * Get one admin
	 * @param adminId
	 * @return
	 */
	@GetMapping("/admins/{adminId}")
	public ResponseDto getAdmin(@PathVariable int adminId){
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Admin found", new Date(), HttpStatus.OK.name(), adminService.getAdmin(adminId));
		return response; 
	}
	
	/**
	 * Add admin
	 * @param admin
	 * @return
	 */
	@PostMapping("/admins")
	public ResponseDto addAdmin(@RequestBody Admin admin){
		// todo: add response when conflict occurs
		Admin resultAdmin = adminService.addAdmin(admin);
		ResponseDto response = new ResponseDto("Admin added", new Date(), HttpStatus.OK.name(), resultAdmin);
		return response;
	}
	
	/**
	 * Update admin
	 * @param admin
	 * @return
	 */
	@PutMapping("/admins")
	public ResponseDto updateAdmin(@RequestBody Admin admin){
		// todo: add response if update not possible
		Admin resultAdmin = adminService.updateAdmin(admin);
		ResponseDto response = new ResponseDto("Admin updated", new Date(), HttpStatus.OK.name(), resultAdmin);
		return response;
	}
	
	/**
	 * Delete admin
	 * @param adminId
	 * @return
	 */
	@DeleteMapping("/admins/{adminId}")
	public ResponseDto updateAdmin(@PathVariable int adminId){
		adminService.deleteAdmin(adminId);
		ResponseDto response = new ResponseDto("Admin is deleted sucessfully with adminId : "+adminId, new Date(),HttpStatus.OK.name(),null); 
		return response;
	}
}
