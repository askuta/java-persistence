package com.epam.jdbcdemo.service;

import java.util.Date;
import java.util.List;

import com.epam.jdbcdemo.dao.AddressDao;
import com.epam.jdbcdemo.dao.FoodDao;
import com.epam.jdbcdemo.dao.MenuDao;
import com.epam.jdbcdemo.dao.MenuFoodDao;
import com.epam.jdbcdemo.dao.RestaurantDao;
import com.epam.jdbcdemo.domain.Address;
import com.epam.jdbcdemo.domain.Food;
import com.epam.jdbcdemo.dto.RestaurantWithAddress;

public class RestaurantService {

	private final AddressDao addressDao;
	private final RestaurantDao restaurantDao;
	private final FoodDao foodDao;
	private final MenuDao menuDao;
	private final MenuFoodDao menuFoodDao;

	public RestaurantService(AddressDao addressDao, RestaurantDao restaurantDao, FoodDao foodDao, MenuDao menuDao, MenuFoodDao menuFoodDao) {
		this.addressDao = addressDao;
		this.restaurantDao = restaurantDao;
		this.foodDao = foodDao;
		this.menuDao = menuDao;
		this.menuFoodDao = menuFoodDao;
	}

	public void save(Address address) {
		addressDao.save(address);
	}

	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		return restaurantDao.getFoodsAvailable(date, restaurantName);
	}

	public void updateFoodPriceByName(String name, int newPrice) {
		foodDao.updateFoodPriceByName(name, newPrice);
	}

	public Food findFoodByName(String name) {
		return foodDao.findFoodByName(name);
	}

	public void removeMenu(int id) {
		menuFoodDao.removeMenuFoods(id);
		menuDao.removeMenu(id);
	}

	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		return restaurantDao.getAllRestaurantsWithAddress();
	}

	public void save(List<Food> foods) {
		foodDao.save(foods);
	}
}
