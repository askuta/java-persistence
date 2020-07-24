package com.epam.jdbcdemo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.jdbcdemo.dao.AddressDao;
import com.epam.jdbcdemo.dao.FoodDao;
import com.epam.jdbcdemo.dao.MenuDao;
import com.epam.jdbcdemo.dao.MenuFoodDao;
import com.epam.jdbcdemo.dao.RestaurantDao;
import com.epam.jdbcdemo.dao.jdbctemplateimpl.JdbcTemplateAddressDao;
import com.epam.jdbcdemo.dao.jdbctemplateimpl.JdbcTemplateFoodDao;
import com.epam.jdbcdemo.dao.jdbctemplateimpl.JdbcTemplateMenuDao;
import com.epam.jdbcdemo.dao.jdbctemplateimpl.JdbcTemplateMenuFoodDao;
import com.epam.jdbcdemo.dao.jdbctemplateimpl.JdbcTemplateRestaurantDao;

@Configuration
public class SpringConfigurationJdbcTemplateDao {

	@Bean
	AddressDao addressDao(DataSource dataSource) {
		return new JdbcTemplateAddressDao(dataSource);
	}

	@Bean
	RestaurantDao restaurantDao(DataSource dataSource) {
		return new JdbcTemplateRestaurantDao(dataSource);
	}
	
	@Bean
	FoodDao foodDao(DataSource dataSource) {
		return new JdbcTemplateFoodDao(dataSource);
	}
	
	@Bean
	MenuDao menuDao(DataSource dataSource) {
		return new JdbcTemplateMenuDao(dataSource);
	}
	
	@Bean
	MenuFoodDao menuFoodDao(DataSource dataSource) {
		return new JdbcTemplateMenuFoodDao(dataSource);
	}
}
