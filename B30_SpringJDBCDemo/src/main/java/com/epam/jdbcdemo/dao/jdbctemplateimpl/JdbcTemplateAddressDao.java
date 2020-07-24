package com.epam.jdbcdemo.dao.jdbctemplateimpl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.jdbcdemo.dao.AddressDao;
import com.epam.jdbcdemo.domain.Address;

public class JdbcTemplateAddressDao extends JdbcDaoSupport implements AddressDao {

	private static final String SQL_INSERT_ADDRESS = "INSERT INTO address (CITY, COUNTRY, STREET, ZIPCODE) VALUES (?, ?, ?, ?)";

	public JdbcTemplateAddressDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void save(Address address) {
		getJdbcTemplate().update(
				SQL_INSERT_ADDRESS,
				address.getCity(), address.getCountry(), address.getStreet(), address.getZipCode());
	}
}
