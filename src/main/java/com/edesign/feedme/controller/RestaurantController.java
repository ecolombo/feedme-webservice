package com.edesign.feedme.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edesign.feedme.dto.ResponseDto;
import com.edesign.feedme.entity.Restaurant;
import com.edesign.feedme.service.RestaurantService;

// CRUD operations for restaurant

@RestController
public class RestaurantController {

	@Autowired 
	RestaurantService restaurantService;
	
	/**
	 * Get all restaurants
	 * @return
	 */
	@GetMapping("/restaurants")
	public ResponseDto getRestaurants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "restaurantId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){		
		Page<Restaurant> paginatedResults = restaurantService.getRestaurants(page, size, sort, sortOrder);
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Restaurants found", new Date(), HttpStatus.OK.name(), paginatedResults); 	
		return response;
	}
	
	
	/**
	 * Get one restaurant
	 * @param restaurantId
	 * @return
	 */
	@GetMapping("/restaurants/{restaurantId}")
	public ResponseDto getRestaurant(@PathVariable int restaurantId){
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Restaurant found", new Date(), HttpStatus.OK.name(), restaurantService.getRestaurant(restaurantId));
		return response; 
	}
	
	/**
	 * Add restaurant
	 * @param restaurant
	 * @return
	 */
	@PostMapping("/restaurants")
	public ResponseDto addRestaurant(@RequestBody Restaurant restaurant){
		// todo: add response when conflict occurs
		Restaurant resultRestaurant = restaurantService.addRestaurant(restaurant);
		ResponseDto response = new ResponseDto("Restaurant added", new Date(), HttpStatus.OK.name(), resultRestaurant);
		return response;
	}
	
	/**
	 * Update restaurant
	 * @param restaurant
	 * @return
	 */
	@PutMapping("/restaurants")
	public ResponseDto updateRestaurant(@RequestBody Restaurant restaurant){
		// todo: add response if update not possible
		Restaurant resultRestaurant = restaurantService.updateRestaurant(restaurant);
		ResponseDto response = new ResponseDto("Restaurant updated", new Date(), HttpStatus.OK.name(), resultRestaurant);
		return response;
	}
	
	/**
	 * Delete restaurant
	 * @param restaurantId
	 * @return
	 */
	@DeleteMapping("/restaurants/{restaurantId}")
	public ResponseDto updateRestaurant(@PathVariable int restaurantId){
		restaurantService.deleteRestaurant(restaurantId);
		ResponseDto response = new ResponseDto("Restaurant is deleted sucessfully with restaurantId : "+restaurantId, new Date(),HttpStatus.OK.name(),null); 
		return response;
	}
}
