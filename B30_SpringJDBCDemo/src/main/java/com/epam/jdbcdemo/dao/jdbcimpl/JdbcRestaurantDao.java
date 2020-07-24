package com.epam.jdbcdemo.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.epam.jdbcdemo.dao.RestaurantDao;
import com.epam.jdbcdemo.domain.Address;
import com.epam.jdbcdemo.domain.Food;
import com.epam.jdbcdemo.domain.Restaurant;
import com.epam.jdbcdemo.dto.RestaurantWithAddress;

public class JdbcRestaurantDao extends GenericJdbcDao implements RestaurantDao {

	private static final String SQL_AVAILABLE_FOODS_BY_RESTAURANT_AND_DATE = //
			"SELECT F.ID, F.CALORIES, F.ISVEGAN, F.NAME, F.PRICE " + //
			"FROM RESTAURANT R " + //
			"JOIN MENU M ON R.ID = M.RESTAURANT_ID " + //
			"JOIN MENU_FOOD MF ON M.ID = MF.MENU_ID " + //
			"JOIN FOOD F ON F.ID = MF.FOOD_ID " + //
			"WHERE R.NAME = ? AND ? BETWEEN M.FROMDATE AND M.TODATE";

	private static final String SQL_ALL_RESTAURANTS_WITH_ADDRESS = //
			"SELECT R.ID AS RESTAURANT_ID, R.NAME, A.ID AS ADDRESS_ID, A.STREET, A.CITY, A.COUNTRY, A.ZIPCODE " + //
			"FROM RESTAURANT R " + //
			"JOIN ADDRESS A ON A.ID = R.ADDRESS_ID";

	public JdbcRestaurantDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		List<Food> foods = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_AVAILABLE_FOODS_BY_RESTAURANT_AND_DATE)) {
			ps.setString(1, restaurantName);
			ps.setDate(2, new java.sql.Date(date.getTime()));

			ps.executeQuery();
			ResultSet resultSet = ps.getResultSet();
			while (resultSet.next()) {
				Food food = new Food();
				food.setId(resultSet.getInt("ID"));
				food.setName(resultSet.getString("NAME"));
				food.setPrice(resultSet.getInt("PRICE"));
				food.setCalories(resultSet.getInt("CALORIES"));
				food.setVegan(resultSet.getBoolean("ISVEGAN"));

				foods.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foods;
	}

	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		List<RestaurantWithAddress> restaurantsWithAddress = new ArrayList<>();

		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_ALL_RESTAURANTS_WITH_ADDRESS)) {

			ps.executeQuery();
			ResultSet resultSet = ps.getResultSet();
			while (resultSet.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setId(resultSet.getInt("RESTAURANT_ID"));
				restaurant.setName(resultSet.getString("NAME"));
				restaurant.setAddressId(resultSet.getInt("ADDRESS_ID"));

				Address address = new Address();
				address.setId(resultSet.getInt("ADDRESS_ID"));
				address.setStreet(resultSet.getString("STREET"));
				address.setCity(resultSet.getString("CITY"));
				address.setCountry(resultSet.getString("COUNTRY"));
				address.setZipCode(resultSet.getString("ZIPCODE"));

				restaurantsWithAddress.add(new RestaurantWithAddress(restaurant, address));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantsWithAddress;
	}
}
