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
@Table(name = "restaurants")
@SequenceGenerator(name="restaurants_seq", sequenceName = "restaurants_seq", initialValue =1, allocationSize = 1 )

public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurants_seq")
	@Column(name="restaurants_id")
	private int restaurantId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@ElementCollection
	@CollectionTable(name="restaurant_images", joinColumns=@JoinColumn(name="restaurants_id"))	
	@Column(name="image_url")
	private List<String> imageUrls = new ArrayList<>();

	@Column(name="thumbnail_image")
	private Integer thumbnailImage;	
	
	@Column(name="addedOn")
	private Date addedOn = new Date();
	
	@ManyToOne
	@JoinColumn(name = "restaurantCategory_id")
	private RestaurantCategory restaurantCategory;

}
