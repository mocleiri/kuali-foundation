/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.cli.dump;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.impex.cli.project.ImpexCLIProjectConfig;
import org.kuali.common.impex.spring.DumpPropertyLocationsConfig;
import org.kuali.common.jdbc.spring.JdbcPropertyLocationsConfig;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.Location;
import org.kuali.common.util.properties.PropertiesService;
import org.kuali.common.util.properties.spring.ProjectPropertiesServiceConfig;
import org.kuali.common.util.spring.main.MainContext;
import org.kuali.common.util.spring.main.ValidatePropertiesLocationExecutable;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ ImpexCLIProjectConfig.class, ProjectPropertiesServiceConfig.class, JdbcPropertyLocationsConfig.class, DumpPropertyLocationsConfig.class })
public class DumpDatabasePSC implements PropertySourceConfig {

	@Autowired
	MainContext context;

	@Autowired
	Project project;

	@Autowired
	PropertiesService service;

	@Autowired
	JdbcPropertyLocationsConfig jdbc;

	@Autowired
	DumpPropertyLocationsConfig dump;

	@Override
	@Bean
	public PropertySource<?> propertySource() {
		List<Location> locations = getLocations();
		Properties properties = service.getProperties(locations);
		return new PropertiesPropertySource("propertiesPropertySource", properties);
	}

	protected List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		locations.addAll(jdbc.propertyLocations());
		locations.addAll(dump.propertyLocations());
		locations.add(getLocation(context.getArgs()));
		return locations;
	}

	protected Location getLocation(String[] args) {
		new ValidatePropertiesLocationExecutable(context, getInvalidArgsMessage()).execute();
		String value = args[0];
		String encoding = ProjectUtils.getEncoding(project);
		return new Location(value, encoding, true);
	}

	protected String getInvalidArgsMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nThis program requires one argument containing a properties file location.\n");
		sb.append("The properties file will usually define at least these 4 properties:\n");
		sb.append("db.vendor=oracle/mysql\n");
		sb.append("jdbc.username=[username]\n");
		sb.append("jdbc.password=[password]\n");
		sb.append("jdbc.url=[JDBC connection URL]\n");
		return sb.toString();
	}

}
