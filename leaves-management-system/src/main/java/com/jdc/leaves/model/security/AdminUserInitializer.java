package com.jdc.leaves.model.security;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class AdminUserInitializer {
	
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public AdminUserInitializer(DataSource dataDataSource) {
		this.dataSource = dataDataSource;
	}

	@EventListener(classes = ContextRefreshedEvent.class)
	void initAdminUser() {
		if (isNoAdminUser()) {
			// Create Admin User
			createAdminUser();
		}
	}
	
	@Transactional
	private void createAdminUser() {
		var insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("account");
		insert.setColumnNames(List.of("name", "email", "password", "role"));
		insert.setGeneratedKeyName("id");
		
		insert.execute(Map.of(
					"name", "Admin",
					"email", "admin@gmail.com",
					"role", "Admin",
					"password", encoder.encode("adminpwd")
				));
	}

	private boolean isNoAdminUser() {
		return new NamedParameterJdbcTemplate(dataSource)
				.queryForObject(
				" select count(id) from account where role = :role ", 
				Map.of("role", "Admin"), 
				Long.class) == 0;
	}

}
