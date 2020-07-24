package com.epam.jdbcdemo.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.epam.jdbcdemo.dao.MenuDao;

public class JdbcMenuDao extends GenericJdbcDao implements MenuDao {

	private static final String SQL_DELETE_MENU_BY_ID = "DELETE FROM MENU WHERE ID = ?";

	public JdbcMenuDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public void removeMenu(int id) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SQL_DELETE_MENU_BY_ID)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
