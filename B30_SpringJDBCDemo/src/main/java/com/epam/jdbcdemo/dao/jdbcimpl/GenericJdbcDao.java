package com.epam.jdbcdemo.dao.jdbcimpl;

import javax.sql.DataSource;

public abstract class GenericJdbcDao {

	protected final DataSource dataSource;

	public GenericJdbcDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
