package org.kuali.common.jdbc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JdbcProject.class)
public class JdbcProperties {

	@Autowired
	JdbcProject jdbcProject;

}
