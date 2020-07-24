package com.epam.jdbcdemo.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.epam.jdbcdemo.dao.FoodDao;
import com.epam.jdbcdemo.domain.Food;

public class JdbcFoodDao extends GenericJdbcDao implements FoodDao {

	private static final String SQL_FIND_FOOD_BY_NAME = "SELECT ID, CALORIES, ISVEGAN, NAME, PRICE FROM FOOD WHERE NAME = ?";
	private static final String SQL_UPDATE_FOOD_PRICE_BY_NAME = "UPDATE FOOD SET PRICE = ? WHERE NAME = ?";
	private static final String SQL_INSERT_FOOD = "INSERT INTO FOOD (CALORIES, ISVEGAN, NAME, PRICE) VALUES (?, ?, ?, ?)";

	public JdbcFoodDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Food findFoodByName(String name) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_FIND_FOOD_BY_NAME)) {
			ps.setString(1, name);

			ps.executeQuery();
			ResultSet resultSet = ps.getResultSet();
			while (resultSet.next()) {
				Food food = new Food();
				food.setId(resultSet.getInt("ID"));
				food.setName(resultSet.getString("NAME"));
				food.setPrice(resultSet.getInt("PRICE"));
				food.setCalories(resultSet.getInt("CALORIES"));
				food.setVegan(resultSet.getBoolean("ISVEGAN"));

				return food;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_FOOD_PRICE_BY_NAME)) {
			ps.setInt(1, newPrice);
			ps.setString(2, name);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(List<Food> foods) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT_FOOD)) {

			connection.setAutoCommit(false);

			// Create one batch (it might be a bad idea in case of a big batch)
			for (Food food : foods) {
				ps.setInt(1, food.getCalories());
				ps.setBoolean(2, food.isVegan());
				ps.setString(3, food.getName());
				ps.setInt(4, food.getPrice());
				ps.addBatch();
			}
			ps.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
