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
import com.edesign.feedme.entity.Dish;
import com.edesign.feedme.service.DishService;

// CRUD operations for dish

@RestController
public class DishController {

	@Autowired 
	DishService dishService;
	
	/**
	 * Get all dishes
	 * @return
	 */
	@GetMapping("/dishes")
	public ResponseDto getDishes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "dishId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){		
		Page<Dish> paginatedResults = dishService.getDishes(page, size, sort, sortOrder);
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Dishes found", new Date(), HttpStatus.OK.name(), paginatedResults); 	
		return response;
	}
	
	
	/**
	 * Get one dish
	 * @param dishId
	 * @return
	 */
	@GetMapping("/dishes/{dishId}")
	public ResponseDto getDish(@PathVariable int dishId){
		// todo: add response when no data found
		ResponseDto response = new ResponseDto("Dish found", new Date(), HttpStatus.OK.name(), dishService.getDish(dishId));
		return response; 
	}
	
	/**
	 * Add dish
	 * @param dish
	 * @return
	 */
	@PostMapping("/dishes")
	public ResponseDto addDish(@RequestBody Dish dish){
		// todo: add response when conflict occurs
		Dish resultDish = dishService.addDish(dish);
		ResponseDto response = new ResponseDto("Dish added", new Date(), HttpStatus.OK.name(), resultDish);
		return response;
	}
	
	/**
	 * Update dish
	 * @param dish
	 * @return
	 */
	@PutMapping("/dishes")
	public ResponseDto updateDish(@RequestBody Dish dish){
		// todo: add response if update not possible
		Dish resultDish = dishService.updateDish(dish);
		ResponseDto response = new ResponseDto("Dish updated", new Date(), HttpStatus.OK.name(), resultDish);
		return response;
	}
	
	/**
	 * Delete dish
	 * @param dishId
	 * @return
	 */
	@DeleteMapping("/dishes/{dishId}")
	public ResponseDto updateDish(@PathVariable int dishId){
		dishService.deleteDish(dishId);
		ResponseDto response = new ResponseDto("Dish is deleted sucessfully with dishId : "+dishId, new Date(),HttpStatus.OK.name(),null); 
		return response;
	}
}
