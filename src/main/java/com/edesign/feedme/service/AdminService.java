package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.AdminRepository;
import com.edesign.feedme.entity.Admin;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	// Get all Admines
	public Page<Admin> getAdmines(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return adminRepository.findAll(pageable);
	}	
	
	
	// Get one Admin by AdminId
	public Admin getAdmin(int categoryId) {
		return adminRepository.findById(categoryId).get();
	}
	
	// Add Admin
	public Admin addAdmin(Admin admin) {
		System.out.println("[AdminService.addAdmin] Trying to create admin with name:"+admin.getEmail());
		if(adminRepository.existsByEmail(admin.getEmail()))
			throw new BadRequestException("Admin with this email already exists.");
		return adminRepository.save(admin);
	}
	
	// Update Admin
	public Admin updateCategory(Admin admin) {
		if(admin.getAdminId() <=0 )
			throw new BadRequestException("AdminId cannot be null or empty.");
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
	
	
}
