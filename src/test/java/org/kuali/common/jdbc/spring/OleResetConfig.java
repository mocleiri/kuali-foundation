package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.SpringContextLoaderExecutable;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesLoaderContext;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

@Configuration
@Import({ JdbcPropertiesConfig.class, OlePropertiesConfig.class })
public class OleResetConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcPropertiesConfig jdbcPropertiesConfig;

	@Autowired
	OlePropertiesConfig olePropertiesConfig;

	@Bean
	public SpringContext oleSpringContext() {

		// Setup the property source for resolving placeholders
		PropertySourceContext psc = new PropertySourceContext();
		psc.setRemoveExistingSources(true);
		psc.setSources(SpringUtils.asList(springPropertySource()));

		// Setup the class containing annotated configuration
		List<Class<?>> annotatedClasses = CollectionUtils.asList(ResetConfig.class, ResetController.class);

		// Setup the Spring context
		SpringContext context = new SpringContext();
		context.setAnnotatedClasses(annotatedClasses);
		context.setPropertySourceContext(psc);
		return context;
	}

	@Bean(initMethod = "execute")
	public Executable springExecutable() {
		String skip = SpringUtils.getProperty(env, "ole.reset.skip", "false");

		SpringContextLoaderExecutable scle = new SpringContextLoaderExecutable();
		scle.setService(new DefaultSpringService());
		scle.setContext(oleSpringContext());
		scle.setSkip(new Boolean(skip));
		return scle;
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		String name = "springPropertySource";
		Properties source = oleResetProperties();
		return new PropertiesPropertySource(name, source);
	}

	@Bean
	public Properties oleResetProperties() {
		Properties properties = new Properties();
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcPropertiesConfig.jdbcProjectProperties());
		pps.add(olePropertiesConfig.oleProjectProperties());
		for (ProjectProperties pp : pps) {
			for (PropertiesLoaderContext ctx : pp.getLoaderContexts()) {
				Properties combined = PropertyUtils.combine(properties, ctx.getProperties());
				ctx.setProperties(combined);
				Properties loaded = PropertyUtils.load(ctx);
				properties.putAll(loaded);
			}
		}
		return properties;
	}

}
