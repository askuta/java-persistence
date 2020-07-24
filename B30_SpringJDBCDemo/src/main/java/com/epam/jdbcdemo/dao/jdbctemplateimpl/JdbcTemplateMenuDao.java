package com.epam.jdbcdemo.dao.jdbctemplateimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.jdbcdemo.dao.MenuDao;

public class JdbcTemplateMenuDao extends JdbcDaoSupport implements MenuDao {

	private static final String SQL_DELETE_MENU_BY_ID = "DELETE FROM MENU WHERE ID = ?";

	public JdbcTemplateMenuDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void removeMenu(int id) {
		getJdbcTemplate().update(SQL_DELETE_MENU_BY_ID, id);
	}
}
