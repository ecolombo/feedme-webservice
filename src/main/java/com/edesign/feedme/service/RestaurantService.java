package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.RestaurantRepository;
import com.edesign.feedme.entity.Restaurant;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	// Get all Restaurantes
	public Page<Restaurant> getRestaurants(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return restaurantRepository.findAll(pageable);
	}	
	
	
	// Get one Restaurant by RestaurantId
	public Restaurant getRestaurant(int restaurantId) {
		return restaurantRepository.findById(restaurantId).get();
	}
	
	// Add Restaurant
	public Restaurant addRestaurant(Restaurant restaurant) {
		System.out.println("[RestaurantService.addRestaurant] Trying to create restaurant with name:"+restaurant.getName());
		if(restaurantRepository.existsByName(restaurant.getName()))
			throw new BadRequestException("Restaurant with this name already exists.");
		return restaurantRepository.save(restaurant);
	}
	
	// Update Restaurant
	public Restaurant updateRestaurant(Restaurant restaurant) {
		if(restaurant.getRestaurantId() <=0 )
			throw new BadRequestException("RestaurantId cannot be null or empty.");
		if(restaurantRepository.existsById(restaurant.getRestaurantId()))
			return restaurantRepository.save(restaurant);
		else 
			throw new NotFoundException("Restaurant does not exist with provided restaurantId.");

	}
	
	// Delete Restaurant
	public void deleteRestaurant(int restaurantId) {
		if(restaurantId <=0 )
			throw new BadRequestException("restaurantId id cannot be null or empty.");
		if(restaurantRepository.existsById(restaurantId))
			restaurantRepository.deleteById(restaurantId);
		else 
			throw new NotFoundException("The restaurant does not exist with provided restaurantId.");
	}
	
	
}
