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
import com.edesign.feedme.entity.RestaurantCategory;
import com.edesign.feedme.service.RestaurantCategoryService;

// CRUD operations for restaurantCategory

@RestController
public class RestaurantCategoryController {

	@Autowired 
	RestaurantCategoryService restaurantCategoryService;
	
	/**
	 * Get all restaurantCategories
	 * @return
	 */
	@GetMapping("/restaurantCategories")
	public ResponseDto getRestaurantCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "restaurantCategoryId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){		
		Page<RestaurantCategory> paginatedResults = restaurantCategoryService.getRestaurantCategories(page, size, sort, sortOrder);
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("RestaurantCategories found", new Date(), HttpStatus.OK.name(), paginatedResults); 	
		return response;
	}
	
	
	/**
	 * Get one restaurantCategory
	 * @param restaurantCategoryId
	 * @return
	 */
	@GetMapping("/restaurantCategories/{restaurantCategoryId}")
	public ResponseDto getRestaurantCategory(@PathVariable int restaurantCategoryId){
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("RestaurantCategory found", new Date(), HttpStatus.OK.name(), restaurantCategoryService.getRestaurantCategory(restaurantCategoryId));
		return response; 
	}
	
	/**
	 * Add restaurantCategory
	 * @param restaurantCategory
	 * @return
	 */
	@PostMapping("/restaurantCategories")
	public ResponseDto addRestaurantCategory(@RequestBody RestaurantCategory restaurantCategory){
		// todo: add response when conflict occurs
		RestaurantCategory resultRestaurantCategory = restaurantCategoryService.addRestaurantCategory(restaurantCategory);
		ResponseDto response = new ResponseDto("RestaurantCategory added", new Date(), HttpStatus.OK.name(), resultRestaurantCategory);
		return response;
	}
	
	/**
	 * Update restaurantCategory
	 * @param restaurantCategory
	 * @return
	 */
	@PutMapping("/restaurantCategories")
	public ResponseDto updateRestaurantCategory(@RequestBody RestaurantCategory restaurantCategory){
		// todo: add response if update not possible
		RestaurantCategory resultRestaurantCategory = restaurantCategoryService.updateRestaurantCategory(restaurantCategory);
		ResponseDto response = new ResponseDto("RestaurantCategory updated", new Date(), HttpStatus.OK.name(), resultRestaurantCategory);
		return response;
	}
	
	/**
	 * Delete restaurantCategory
	 * @param restaurantCategoryId
	 * @return
	 */
	@DeleteMapping("/restaurantCategories/{restaurantCategoryId}")
	public ResponseDto updateRestaurantCategory(@PathVariable int restaurantCategoryId){
		restaurantCategoryService.deleteRestaurantCategory(restaurantCategoryId);
		ResponseDto response = new ResponseDto("RestaurantCategory is deleted sucessfully with restaurantCategoryId : "+restaurantCategoryId, new Date(),HttpStatus.OK.name(),null); 
		return response;
	}
}
