package com.edesign.feedme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edesign.feedme.entity.RestaurantCategory;


public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Integer>{

	boolean existsByName(String name);
	RestaurantCategory findByName(String name);

}
