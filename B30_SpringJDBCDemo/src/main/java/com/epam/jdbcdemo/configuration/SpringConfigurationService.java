package com.epam.jdbcdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.jdbcdemo.dao.AddressDao;
import com.epam.jdbcdemo.dao.FoodDao;
import com.epam.jdbcdemo.dao.MenuDao;
import com.epam.jdbcdemo.dao.MenuFoodDao;
import com.epam.jdbcdemo.dao.RestaurantDao;
import com.epam.jdbcdemo.service.RestaurantService;

@Configuration
public class SpringConfigurationService {
	
	@Bean
	public RestaurantService restaurantService(AddressDao addressDao, RestaurantDao restaurantDao, FoodDao foodDao, MenuDao menuDao, MenuFoodDao menuFoodDao) {
		return new RestaurantService(addressDao, restaurantDao, foodDao, menuDao, menuFoodDao);
	}	
}
