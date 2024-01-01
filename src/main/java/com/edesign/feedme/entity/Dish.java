package com.edesign.feedme.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dishes")
@SequenceGenerator(name="dishes_seq", sequenceName = "dishes_seq", initialValue =1, allocationSize = 1 )

public class Dish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishes_seq")
	@Column(name="dishes_id")
	private int dishId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	@Column(name="imageURL")
	private String imageURL;
	
	@Column(name="dish_price")
	private double price;
	
	@Column(name="addedOn")
	private Date addedOn = new Date();
	
	// Rating --> Rating_ID
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;	

}
