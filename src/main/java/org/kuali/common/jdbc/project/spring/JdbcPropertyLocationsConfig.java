package org.kuali.common.jdbc.project.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.project.JdbcProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesLocationService;
import org.kuali.common.util.properties.spring.PropertiesLocationServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.google.common.collect.ImmutableList;

@Configuration
@Import({ PropertiesLocationServiceConfig.class })
public class JdbcPropertyLocationsConfig {

	private static final ProjectIdentifier SQL = JdbcProjectConstants.KUALI_SQL;
	private static final ProjectIdentifier JDBC = JdbcProjectConstants.PROJECT_ID;

	@Autowired
	PropertiesLocationService service;

	@Bean
	public List<Location> jdbcPropertyLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.addAll(service.getLocations(SQL, getSqlFilenames()));
		locations.addAll(service.getLocations(JDBC, "config.properties"));
		return ImmutableList.copyOf(locations);
	}

	protected List<String> getSqlFilenames() {
		List<String> filenames = new ArrayList<String>();
		filenames.add("derby.xml");
		filenames.add("h2.xml");
		filenames.add("mysql.xml");
		filenames.add("oracle.xml");
		filenames.add("sql.xml");
		return filenames;
	}

}
