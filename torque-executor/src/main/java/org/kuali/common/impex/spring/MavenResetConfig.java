package org.kuali.common.impex.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.jdbc.spring.ResetConfig;
import org.kuali.common.jdbc.spring.ResetController;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.SpringExecutable;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcPropertiesConfig.class, GeneratorPropertiesConfig.class })
public class MavenResetConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcPropertiesConfig jdbcProperties;

	@Autowired
	GeneratorPropertiesConfig generatorProperties;

	@Autowired
	Properties mavenProperties;

	@Bean
	public ProjectProperties mavenProjectProperties() {
		Project project = ProjectUtils.getProject(mavenProperties);

		List<String> excludes = getList(env, "properties.maven.exclude");
		PropertyUtils.trim(mavenProperties, null, excludes);

		PropertiesContext pc = new PropertiesContext();
		pc.setProperties(mavenProperties);

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	protected List<String> getList(Environment env, String key) {
		String csv = env.getProperty(key);
		return CollectionUtils.getTrimmedListFromCSV(csv);
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		// Combine project properties into a list where the "last one in wins"
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcProperties.jdbcProjectProperties());
		pps.add(generatorProperties.generatorProjectProperties());
		pps.add(mavenProjectProperties());

		// Get a PropertySource object backed by the properties loaded from the list
		return SpringUtils.getPropertySource("springPropertySource", pps);
	}

	@Bean(initMethod = "execute")
	public Executable springExecutable() {
		// Simple flag for skipping execution altogether
		boolean skip = SpringUtils.getBoolean(env, "db.reset.skip", false);

		/**
		 * This line creates a property source containing 100% of the properties needed by Spring to resolve any/all placeholders. It will be the only property source available to
		 * Spring so it needs to include system properties and environment variables
		 */
		PropertySourceContext psc = new PropertySourceContext(springPropertySource(), true);

		// Setup the list containing annotated configuration classes
		List<Class<?>> annotatedClasses = CollectionUtils.asList(MpxSupplierConfig.class, ResetConfig.class, ResetController.class);

		// Setup the Spring context
		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(annotatedClasses);
		context.setPropertySourceContext(psc);

		// Load the context
		SpringExecutable se = new SpringExecutable();
		se.setService(new DefaultSpringService());
		se.setContext(context);
		se.setSkip(skip);
		return se;
	}

}
