package com.epam.jdbcdemo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.jdbcdemo.dao.AddressDao;
import com.epam.jdbcdemo.dao.FoodDao;
import com.epam.jdbcdemo.dao.MenuDao;
import com.epam.jdbcdemo.dao.MenuFoodDao;
import com.epam.jdbcdemo.dao.RestaurantDao;
import com.epam.jdbcdemo.dao.jdbcimpl.JdbcAddressDao;
import com.epam.jdbcdemo.dao.jdbcimpl.JdbcFoodDao;
import com.epam.jdbcdemo.dao.jdbcimpl.JdbcMenuDao;
import com.epam.jdbcdemo.dao.jdbcimpl.JdbcMenuFoodDao;
import com.epam.jdbcdemo.dao.jdbcimpl.JdbcRestaurantDao;

@Configuration
public class SpringConfigurationJdbcDao {

	@Bean
	AddressDao addressDao(DataSource dataSource) {
		return new JdbcAddressDao(dataSource);
	}

	@Bean
	RestaurantDao restaurantDao(DataSource dataSource) {
		return new JdbcRestaurantDao(dataSource);
	}
	
	@Bean
	FoodDao foodDao(DataSource dataSource) {
		return new JdbcFoodDao(dataSource);
	}
	
	@Bean
	MenuDao menuDao(DataSource dataSource) {
		return new JdbcMenuDao(dataSource);
	}
	
	@Bean
	MenuFoodDao menuFoodDao(DataSource dataSource) {
		return new JdbcMenuFoodDao(dataSource);
	}
}
