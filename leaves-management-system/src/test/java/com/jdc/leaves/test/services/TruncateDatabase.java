package com.jdc.leaves.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "/root-config.xml")
public class TruncateDatabase {

	@Test
	@Sql(scripts = {
			"/sql/truncate.sql"
	})
	void truncate() {
		assertEquals(1, 1);
	}
	// 09782003098
}
