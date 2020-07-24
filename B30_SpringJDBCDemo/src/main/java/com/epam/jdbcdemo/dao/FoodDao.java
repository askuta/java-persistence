package com.epam.jdbcdemo.dao;

import java.util.List;

import com.epam.jdbcdemo.domain.Food;

public interface FoodDao {

	Food findFoodByName(String name);

	void updateFoodPriceByName(String name, int newPrice);

	void save(List<Food> foods);

}
