package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OlePropertiesConfig {

	@Bean
	public ProjectProperties oleProjectProperties() {
		Project project = new Project();
		project.setArtifactId("ole-fs");
		project.setGroupId("org.kuali.ole");
		project.setVersion("0.8.1-s-r11074");
		project.setEncoding("UTF-8");

		List<String> locations = new ArrayList<String>();
		locations.add("classpath:ole-fs.properties");

		PropertiesContext plc = new PropertiesContext();
		plc.setEncoding(project.getEncoding());
		plc.setLocations(locations);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setLoaderContexts(Arrays.asList(plc));
		return pp;
	}

}
