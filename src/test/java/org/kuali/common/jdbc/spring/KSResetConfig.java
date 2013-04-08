package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Project;
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
@Import({ JdbcPropertiesConfig.class })
public class KSResetConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcPropertiesConfig jdbcPropertiesConfig;

	@Bean
	public ProjectProperties ksProjectProperties() {
		Project project = new Project();
		project.setGroupId("org.kuali.student");
		project.setArtifactId("ks-with-rice-bundled");
		project.setVersion("1.0.0");
		project.setEncoding("UTF-8");

		PropertiesContext pc = new PropertiesContext();
		pc.setEncoding(project.getEncoding());
		pc.setLocations(Arrays.asList("classpath:ole-fs.properties"));

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		// Combine project properties into a list where the "last one in wins"
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcPropertiesConfig.jdbcProjectProperties());
		pps.add(ksProjectProperties());

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
		List<Class<?>> annotatedClasses = CollectionUtils.asList(ResetConfig.class, ResetController.class);

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
