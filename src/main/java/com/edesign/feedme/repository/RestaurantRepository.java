package com.edesign.feedme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edesign.feedme.entity.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	boolean existsByName(String name);
	Restaurant findByName(String name);

}
