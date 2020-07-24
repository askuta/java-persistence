package com.epam.jdbcdemo.dao.jdbctemplateimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.jdbcdemo.dao.MenuFoodDao;

public class JdbcTemplateMenuFoodDao extends JdbcDaoSupport implements MenuFoodDao {

	private static final String SQL_DELETE_MENU_FOOD_BY_MENU_ID = "DELETE FROM MENU_FOOD WHERE MENU_ID = ?";

	public JdbcTemplateMenuFoodDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void removeMenuFoods(int id) {
		getJdbcTemplate().update(SQL_DELETE_MENU_FOOD_BY_MENU_ID, id);
	}
}
