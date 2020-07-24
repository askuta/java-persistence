package com.epam.jdbcdemo.dao.jdbctemplateimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.jdbcdemo.dao.FoodDao;
import com.epam.jdbcdemo.domain.Food;

public class JdbcTemplateFoodDao extends JdbcDaoSupport implements FoodDao {

	private static final String SQL_FIND_FOOD_BY_NAME = "SELECT ID, CALORIES, ISVEGAN, NAME, PRICE FROM FOOD WHERE NAME = ?";
	private static final String SQL_UPDATE_FOOD_PRICE_BY_NAME = "UPDATE FOOD SET PRICE = ? WHERE NAME = ?";
	private static final String SQL_INSERT_FOOD = "INSERT INTO FOOD (CALORIES, ISVEGAN, NAME, PRICE) VALUES (?, ?, ?, ?)";

	public JdbcTemplateFoodDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Food findFoodByName(String name) {
		return getJdbcTemplate().queryForObject(
				SQL_FIND_FOOD_BY_NAME,
				new Object[]{name},
				new RowMapper<Food>() {

					public Food mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						Food food = new Food();
						food.setId(resultSet.getInt("ID"));
						food.setName(resultSet.getString("NAME"));
						food.setPrice(resultSet.getInt("PRICE"));
						food.setCalories(resultSet.getInt("CALORIES"));
						food.setVegan(resultSet.getBoolean("ISVEGAN"));
						return food;
					}
				});
	}

	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		getJdbcTemplate().update(
				SQL_UPDATE_FOOD_PRICE_BY_NAME,
				newPrice, name);
	}

	@Override
	public void save(List<Food> foods) {
		getJdbcTemplate().batchUpdate(
				SQL_INSERT_FOOD,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int index) throws SQLException {
						ps.setInt(1, foods.get(index).getCalories());
						ps.setBoolean(2, foods.get(index).isVegan());
						ps.setString(3, foods.get(index).getName());
						ps.setInt(4, foods.get(index).getPrice());
					}

					@Override
					public int getBatchSize() {
						return 10;
					}
				});
	}
}
