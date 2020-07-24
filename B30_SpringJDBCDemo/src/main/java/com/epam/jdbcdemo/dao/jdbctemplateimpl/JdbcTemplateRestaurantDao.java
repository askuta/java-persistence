package com.epam.jdbcdemo.dao.jdbctemplateimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.jdbcdemo.dao.RestaurantDao;
import com.epam.jdbcdemo.domain.Address;
import com.epam.jdbcdemo.domain.Food;
import com.epam.jdbcdemo.domain.Restaurant;
import com.epam.jdbcdemo.dto.RestaurantWithAddress;

public class JdbcTemplateRestaurantDao extends JdbcDaoSupport implements RestaurantDao {

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

	public JdbcTemplateRestaurantDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		return (List<Food>) getJdbcTemplate().query(
				SQL_AVAILABLE_FOODS_BY_RESTAURANT_AND_DATE,
				new Object[] { restaurantName, new java.sql.Date(date.getTime()) },
				new FoodMapper());
	}

	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		return (List<RestaurantWithAddress>) getJdbcTemplate().query(
				SQL_ALL_RESTAURANTS_WITH_ADDRESS,
				new RestaurantWithAddressMapper());
	}

	private static final class FoodMapper implements RowMapper<Food> {

		@Override
		public Food mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Food food = new Food();
			food.setId(resultSet.getInt("ID"));
			food.setName(resultSet.getString("NAME"));
			food.setPrice(resultSet.getInt("PRICE"));
			food.setCalories(resultSet.getInt("CALORIES"));
			food.setVegan(resultSet.getBoolean("ISVEGAN"));

			return food;
		}
	}

	private static final class RestaurantWithAddressMapper implements RowMapper<RestaurantWithAddress> {

		@Override
		public RestaurantWithAddress mapRow(ResultSet resultSet, int rowNum) throws SQLException {
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

			return new RestaurantWithAddress(restaurant, address);
		}
	}
}
