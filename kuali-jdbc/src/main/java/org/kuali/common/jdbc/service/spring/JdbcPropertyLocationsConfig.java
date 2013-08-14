package org.kuali.common.jdbc.service.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.jdbc.config.JdbcProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesLocationService;
import org.kuali.common.util.properties.spring.PropertiesLocationServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PropertiesLocationServiceConfig.class })
public class JdbcPropertyLocationsConfig {

	@Autowired
	PropertiesLocationService service;

	@Bean
	public List<Location> jdbcPropertyLocations() {
		ProjectIdentifier pid = JdbcProjectConstants.KUALI_SQL_PROJECT_IDENTIFIER;
		List<Location> locations = service.getLocations(pid, getKualiSqlFilenames());
		return Collections.unmodifiableList(locations);
	}

	protected List<String> getKualiSqlFilenames() {
		List<String> filenames = new ArrayList<String>();
		filenames.add("derby.xml");
		filenames.add("h2.xml");
		filenames.add("mysql.xml");
		filenames.add("oracle.xml");
		filenames.add("sql.xml");
		return filenames;
	}

}
