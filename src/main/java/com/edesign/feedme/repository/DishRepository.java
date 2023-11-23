package com.edesign.feedme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edesign.feedme.entity.Dish;


public interface DishRepository extends JpaRepository<Dish, Integer>{

	boolean existsByName(String name);
	Dish findByName(String name);

}
