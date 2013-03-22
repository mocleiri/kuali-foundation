package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesLoaderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JdbcProject.class)
public class JdbcProperties {

	@Autowired
	JdbcProject jdbcProject;

	@Bean
	public ProjectProperties jdbcProjectProperties() {
		Project project = jdbcProject.jdbcProject();

		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/common/jdbc/jdbc.properties");
		locations.add("classpath:org/kuali/common/jdbc/service.properties");
		locations.add("classpath:org/kuali/common/sql/sql.xml");
		locations.add("classpath:org/kuali/common/sql/mysql.xml");
		locations.add("classpath:org/kuali/common/sql/oracle.xml");
		locations.add("classpath:org/kuali/common/sql/h2.xml");
		locations.add("classpath:org/kuali/common/sql/derby.xml");

		PropertiesLoaderContext plc = new PropertiesLoaderContext();
		plc.setEncoding(project.getEncoding());
		plc.setLocations(locations);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setLoaderContexts(Arrays.asList(plc));
		return pp;
	}

}
