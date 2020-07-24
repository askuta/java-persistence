package com.epam.jdbcdemo.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.epam.jdbcdemo.helper.DatabaseCreator;

@Configuration
public class SpringConfigurationDataSource {

	private static final String URL = "jdbc:h2:~/mydatabase";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	@Bean
	public DataSource dataSource() {
		return new DriverManagerDataSource(URL, USER, PASSWORD);
	}

	@Bean
	public DatabaseCreator databaseCreator(DataSource dataSource) {
		return new DatabaseCreator(dataSource);
	}
}
