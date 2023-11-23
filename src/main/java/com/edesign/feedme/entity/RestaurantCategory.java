package com.edesign.feedme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
@Table(name = "retaurantCategories")
@SequenceGenerator(name="restaurant_category_seq", sequenceName = "restaurant_category_seq", initialValue =1, allocationSize = 1 )
public class RestaurantCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "retaurant_category_seq")
	@Column(name="restaurant_category_id")
	private int restaurantCategoryId;
	
	@Column(name="name")
	private String name;

}
