package com.epam.jdbcdemo.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.epam.jdbcdemo.dao.MenuFoodDao;

public class JdbcMenuFoodDao extends GenericJdbcDao implements MenuFoodDao {

	private static final String SQL_DELETE_MENU_FOOD_BY_MENU_ID = "DELETE FROM MENU_FOOD WHERE MENU_ID = ?";

	public JdbcMenuFoodDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void removeMenuFoods(int menuId) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE_MENU_FOOD_BY_MENU_ID)) {
			ps.setInt(1, menuId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
