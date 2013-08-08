package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.config.JdbcProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.spring.PropertyLocationsCommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ PropertyLocationsCommonConfig.class })
public class JdbcPropertyLocationsConfig {

	@Autowired
	PropertyLocationsCommonConfig propertyLocationsCommonConfig;

	@Bean
	public List<Location> jdbcPropertyLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.addAll(getKualiSqlLocations());
		locations.addAll(getJdbcSqlLocations());
		return locations;
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

	protected List<Location> getJdbcSqlLocations() {
		List<String> filenames = getJdbcList();
		ProjectIdentifier identifier = JdbcProjectConstants.PROJECT_IDENTIFIER;
		return propertyLocationsCommonConfig.getLocations(identifier, filenames);
	}

	protected List<Location> getKualiSqlLocations() {
		List<String> filenames = getKualiSqlList();
		ProjectIdentifier identifier = JdbcProjectConstants.KUALI_SQL_PROJECT_IDENTIFIER;
		return propertyLocationsCommonConfig.getLocations(identifier, filenames);
	}

}
