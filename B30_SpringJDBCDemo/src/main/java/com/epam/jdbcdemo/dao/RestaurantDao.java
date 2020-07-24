package com.epam.jdbcdemo.dao;

import java.util.Date;
import java.util.List;

import com.epam.jdbcdemo.domain.Food;
import com.epam.jdbcdemo.dto.RestaurantWithAddress;

public interface RestaurantDao {

	List<Food> getFoodsAvailable(Date date, String restaurantName);

	List<RestaurantWithAddress> getAllRestaurantsWithAddress();

}
