package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.AdminRepository;
import com.edesign.feedme.dto.LoginRequestDto;
import com.edesign.feedme.entity.Admin;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();	
	
	// Get all Admines
	public Page<Admin> getAdmins(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return adminRepository.findAll(pageable);
	}	
	
	
	// Get one Admin by AdminId
	public Admin getAdmin(int adminId) {
		return adminRepository.findById(adminId).get();
	}
	
	// Add Admin
	public Admin addAdmin(Admin admin) {
		System.out.println("[AdminService.addAdmin] Trying to create admin with name:"+admin.getEmail());
		if(adminRepository.existsByEmail(admin.getEmail()))
			throw new BadRequestException("Admin with this email already exists.");
		// Encode
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}
	
	// Update Admin
	public Admin updateAdmin(Admin admin) {
		if(admin.getAdminId() <=0 )
			throw new BadRequestException("AdminId cannot be null or empty.");
		// Encode
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		if(adminRepository.existsById(admin.getAdminId()))
			return adminRepository.save(admin);
		else 
			throw new NotFoundException("Admin does not exist with provided adminId.");

	}
	
	// Delete Admin
	public void deleteAdmin(int adminId) {
		if(adminId <=0 )
			throw new BadRequestException("adminId id cannot be null or empty.");
		if(adminRepository.existsById(adminId))
			adminRepository.deleteById(adminId);
		else 
			throw new NotFoundException("The admin does not exist with provided adminId.");
	}
	
	// Validate Login
	public Admin validateLogin(LoginRequestDto loginDto) {
		
		boolean exist = adminRepository.existsByEmail(loginDto.getEmail()) ;
		if(exist) {
			Admin admin = adminRepository.findByEmail(loginDto.getEmail());
			// passwordEncoder = new BCryptPasswordEncoder();
			if(passwordEncoder.matches(loginDto.getPassword(), admin.getPassword())) {
				return admin;
			} else {
				throw new NotFoundException("Invalid password, password mismatch error.");
			}
		} else {
			throw new NotFoundException("Admin user with this email adress does not exist.");
		}
	}
	
	
}
