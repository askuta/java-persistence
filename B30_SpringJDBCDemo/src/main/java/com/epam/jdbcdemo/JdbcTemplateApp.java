package com.epam.jdbcdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.epam.jdbcdemo.configuration.SpringConfigurationDataSource;
import com.epam.jdbcdemo.configuration.SpringConfigurationJdbcTemplateDao;
import com.epam.jdbcdemo.configuration.SpringConfigurationService;

public class JdbcTemplateApp extends JdbcApp {

	public static void main(String[] args) {
		JdbcTemplateApp app = new JdbcTemplateApp();
		app.run();
	}

	@Override
	protected AbstractApplicationContext createSpringContext() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationDataSource.class,
				SpringConfigurationJdbcTemplateDao.class, SpringConfigurationService.class);
		return context;
	}
}
