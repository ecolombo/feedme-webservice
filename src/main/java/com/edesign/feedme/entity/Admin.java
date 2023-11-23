package com.edesign.feedme.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admins")
@SequenceGenerator(name="admin_seq", sequenceName = "admin_seq", initialValue =1, allocationSize = 1 )
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
	@Column(name="auth_token")
	private String authToken = UUID.randomUUID().toString();
	
//	@Column(name="login_type")
//	private int loginType;
	
	@Column(name="added_on")
	private Date addedOn = new Date();
}
