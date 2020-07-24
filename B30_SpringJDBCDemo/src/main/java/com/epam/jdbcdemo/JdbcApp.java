package com.epam.jdbcdemo;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.epam.jdbcdemo.configuration.SpringConfigurationDataSource;
import com.epam.jdbcdemo.configuration.SpringConfigurationJdbcDao;
import com.epam.jdbcdemo.configuration.SpringConfigurationService;
import com.epam.jdbcdemo.domain.Address;
import com.epam.jdbcdemo.helper.DatabaseCreator;
import com.epam.jdbcdemo.service.RestaurantService;

public class JdbcApp {

	public static void main(String[] args) {
		JdbcApp app = new JdbcApp();
		app.run();
	}

	protected void run() {
		AbstractApplicationContext context = createSpringContext();

		RestaurantService restaurantService = context.getBean(RestaurantService.class);

		DatabaseCreator databaseCreator = context.getBean(DatabaseCreator.class);
		databaseCreator.createAndPopulateDatabase();

		Address address = new Address();
		address.setCity("Budapest");
		address.setCountry("HU");
		address.setStreet("Futo utca");
		address.setZipCode("1085");

		restaurantService.save(address);

		System.out.println("Retreived id of saved address: " + address.getId());

		System.out.println("Number of foods before delete menu #1: " + restaurantService.getFoodsAvailable(new Date(), "10 MINUTES").size());
		restaurantService.removeMenu(1);
		System.out.println("Number of foods after delete menu #1: " + restaurantService.getFoodsAvailable(new Date(), "10 MINUTES").size());

		System.out.println("Price before update food: " + restaurantService.findFoodByName("Tender lion").getPrice());
		restaurantService.updateFoodPriceByName("Tender lion", 7000);
		System.out.println("Price after update food: " + restaurantService.findFoodByName("Tender lion").getPrice());

		System.out.println("Restaurants: " + restaurantService.getAllRestaurantsWithAddress());

		restaurantService.removeMenu(1);

		context.close();
	}

	protected AbstractApplicationContext createSpringContext() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationDataSource.class,
				SpringConfigurationJdbcDao.class, SpringConfigurationService.class);
		return context;
	}
}
