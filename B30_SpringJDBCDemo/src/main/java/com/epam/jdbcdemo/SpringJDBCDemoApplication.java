package com.epam.jdbcdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJDBCDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJDBCDemoApplication.class, args);

		DataSource dataSource = context.getBean(DataSource.class);
		Statement statement = null;
		ResultSet resultSet = null;
		try (Connection connection = dataSource.getConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT 1 + 1");

			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
