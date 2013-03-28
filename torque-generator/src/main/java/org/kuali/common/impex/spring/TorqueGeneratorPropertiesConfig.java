package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TorqueGeneratorProjectConfig.class)
public class TorqueGeneratorPropertiesConfig {

	@Autowired
	TorqueGeneratorProjectConfig projectConfig;

	@Bean
	public ProjectProperties jdbcProjectProperties() {
		Project project = projectConfig.torqueGeneratorProject();

		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/common/impex/batch.properties");

		PropertiesContext pc = new PropertiesContext();
		pc.setEncoding(project.getEncoding());
		pc.setLocations(locations);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

}
