package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.spring.AbstractMavenResetConfig;
import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.jdbc.spring.ResetConfig;
import org.kuali.common.jdbc.spring.ResetController;
import org.kuali.common.util.property.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcPropertiesConfig.class, GeneratorPropertiesConfig.class })
public class MavenResetConfig extends AbstractMavenResetConfig {

	@Autowired
	GeneratorPropertiesConfig generatorProperties;

	@Override
	protected List<ProjectProperties> getProjectPropertiesList() {
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcProperties.jdbcProjectProperties());
		pps.add(generatorProperties.generatorProjectProperties());
		pps.add(mavenProjectProperties());
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
