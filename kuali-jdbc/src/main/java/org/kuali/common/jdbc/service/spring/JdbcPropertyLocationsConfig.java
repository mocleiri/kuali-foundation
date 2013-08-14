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
		List<Location> locations = new ArrayList<Location>();
		locations.addAll(getKualiSqlLocations());
		locations.addAll(getJdbcLocations());
		return Collections.unmodifiableList(locations);
	}

	protected List<Location> getJdbcLocations() {
		List<String> filenames = getJdbcList();
		ProjectIdentifier pid = JdbcProjectConstants.PROJECT_IDENTIFIER;
		return service.getLocations(pid, filenames);
	}

	protected List<Location> getKualiSqlLocations() {
		List<String> filenames = getKualiSqlList();
		ProjectIdentifier pid = JdbcProjectConstants.KUALI_SQL_PROJECT_IDENTIFIER;
		return service.getLocations(pid, filenames);
	}

	protected List<String> getJdbcList() {
		List<String> filenames = new ArrayList<String>();
		filenames.add("jdbc.properties");
		return filenames;
	}

	protected List<String> getKualiSqlList() {
		List<String> filenames = new ArrayList<String>();
		filenames.add("derby.xml");
		filenames.add("h2.xml");
		filenames.add("mysql.xml");
		filenames.add("oracle.xml");
		filenames.add("sql.xml");
		return filenames;
	}

}
