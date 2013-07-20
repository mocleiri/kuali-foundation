package org.kuali.common.util.spring.profile;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.PrintMessageExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DatabaseConfig.class)
public class ShowDatabaseConfig {

	@Autowired
	DatabaseConfig databaseConfig;

	@Bean
	public Executable showDatabaseConfigExecutable() {
		Database database = databaseConfig.databaseConfigDatabase();
		PrintMessageExecutable exec = new PrintMessageExecutable();
		exec.setMessage(database.getVendor());
		return exec;
	}
}
