package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.jdbc.spring.AbstractMavenResetConfig;
import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.jdbc.spring.ResetConfig;
import org.kuali.common.jdbc.spring.ResetController;
import org.kuali.common.util.Project;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcPropertiesConfig.class, GeneratorPropertiesConfig.class })
public class KSResetConfig extends AbstractMavenResetConfig {

	@Autowired
	GeneratorPropertiesConfig generatorProperties;

	@Override
	protected List<ProjectProperties> getProjectPropertiesList() {
		Project project = new Project();
		project.setGroupCode("org.kuali.student");
		project.setArtifactId("ks-with-rice-bundled");
		project.setVersion("1.0.0");

		PropertiesContext pc = new PropertiesContext();
		pc.setLocations(Arrays.asList("classpath:ks-with-rice-bundled.properties"));

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);

		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcProperties.jdbcProjectProperties());
		pps.add(generatorProperties.generatorProjectProperties());
		pps.add(pp);
		return pps;
	}

	@Override
	protected List<Class<?>> getAnnotatedClasses() {
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		annotatedClasses.add(MpxSupplierConfig.class);
		annotatedClasses.add(ResetConfig.class);
		annotatedClasses.add(ResetController.class);
		return annotatedClasses;

	}
}
