package com.edesign.feedme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edesign.feedme.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
	User findByEmail(String email);

}
