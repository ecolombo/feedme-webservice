package com.edesign.feedme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edesign.feedme.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer>{

	boolean existsByEmail(String email);
	Admin findByEmail(String email);

}
