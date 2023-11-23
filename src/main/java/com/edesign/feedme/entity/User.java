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
@Table(name = "user")
@SequenceGenerator(name="user_seq", sequenceName = "user_seq", initialValue =1, allocationSize = 1 )
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@Column(name="user_id")
	private int userId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="full_name")
	private String fullName;
	
//	@Column(name="login_type_identifier")
//	private int loginType;
	
	@Column(name="auth_token")
	private String authToken = UUID.randomUUID().toString();
	
	@Column(name="added_on")
	private Date addedOn = new Date();
	
/*	private String street;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String image;
	private Long contact; */
}
