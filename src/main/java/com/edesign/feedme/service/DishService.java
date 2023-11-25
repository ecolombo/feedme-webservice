package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.DishRepository;
import com.edesign.feedme.entity.Dish;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class DishService {
	
	@Autowired
	DishRepository dishRepository;
	
	// Get all Dishes
	public Page<Dish> getDishes(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return dishRepository.findAll(pageable);
	}	
	
	
	// Get one Dish by DishId
	public Dish getDish(int dishId) {
		return dishRepository.findById(dishId).get();
	}
	
	// Add Dish
	public Dish addDish(Dish dish) {
		System.out.println("[DishService.addDish] Trying to create dish with name:"+dish.getName());
		if(dishRepository.existsByName(dish.getName()))
			throw new BadRequestException("Dish with this name already exists.");
		return dishRepository.save(dish);
	}
	
	// Update Dish
	public Dish updateDish(Dish dish) {
		if(dish.getDishId() <=0 )
			throw new BadRequestException("DishId cannot be null or empty.");
		if(dishRepository.existsById(dish.getDishId()))
			return dishRepository.save(dish);
		else 
			throw new NotFoundException("Dish does not exist with provided dishId.");

	}
	
	// Delete Dish
	public void deleteDish(int dishId) {
		if(dishId <=0 )
			throw new BadRequestException("dishId id cannot be null or empty.");
		if(dishRepository.existsById(dishId))
			dishRepository.deleteById(dishId);
		else 
			throw new NotFoundException("The dish does not exist with provided dishId.");
	}
	
	
}
