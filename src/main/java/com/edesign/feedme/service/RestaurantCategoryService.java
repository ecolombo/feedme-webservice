package com.edesign.feedme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.feedme.repository.RestaurantCategoryRepository;
import com.edesign.feedme.entity.RestaurantCategory;
import com.edesign.feedme.exception.BadRequestException;
import com.edesign.feedme.exception.NotFoundException;

@Service
public class RestaurantCategoryService {
	
	@Autowired
	RestaurantCategoryRepository restaurantCategoryRepository;
	
	// Get all Categories
	public Page<RestaurantCategory> getCategories(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return restaurantCategoryRepository.findAll(pageable);
	}	
	
	
	// Get one Category by CategoryId
	public RestaurantCategory getCategory(int restaurantCategoryId) {
		return restaurantCategoryRepository.findById(restaurantCategoryId).get();
	}
	
	// Add Category
	public RestaurantCategory addCategory(RestaurantCategory restaurantCategory) {
		System.out.println("[RestaurantCategoryService.addCategory] Trying to create category with name:"+restaurantCategory.getName());
		if(restaurantCategoryRepository.existsByName(restaurantCategory.getName()))
			throw new BadRequestException("Category with this name already exists.");
		return restaurantCategoryRepository.save(restaurantCategory);
	}
	
	// Update Category
	public RestaurantCategory updateCategory(RestaurantCategory restaurantCategory) {
		if(restaurantCategory.getRestaurantCategoryId() <=0 )
			throw new BadRequestException("CategoryId cannot be null or empty.");
		if(restaurantCategoryRepository.existsById(restaurantCategory.getRestaurantCategoryId()))
			return restaurantCategoryRepository.save(restaurantCategory);
		else 
			throw new NotFoundException("Category does not exist with provided categoryId.");

	}
	
	// Delete Category
	public void deleteRestaurantCategory(int restaurantCategoryId) {
		if(restaurantCategoryId <=0 )
			throw new BadRequestException("categoryId id cannot be null or empty.");
		if(restaurantCategoryRepository.existsById(restaurantCategoryId))
			restaurantCategoryRepository.deleteById(restaurantCategoryId);
		else 
			throw new NotFoundException("The category does not exist with provided categoryId.");
	}
	
	
}
