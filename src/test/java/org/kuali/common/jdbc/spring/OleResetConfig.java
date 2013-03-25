package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.EncUtils;
import org.kuali.common.util.EncryptionMode;
import org.kuali.common.util.EncryptionStrength;
import org.kuali.common.util.Project;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.SpringContextLoaderExecutable;
import org.kuali.common.util.property.ProjectProperties;
import org.kuali.common.util.property.PropertiesContext;
import org.kuali.common.util.property.processor.ResolvePlaceholdersProcessor;
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
@Import({ JdbcPropertiesConfig.class })
public class OleResetConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcPropertiesConfig jdbcPropertiesConfig;

	@Bean
	public ProjectProperties oleProjectProperties() {
		Project project = new Project();
		project.setArtifactId("ole-fs");
		project.setGroupId("org.kuali.ole");
		project.setVersion("0.8.1-s-r11074");
		project.setEncoding("UTF-8");

		PropertiesContext pc = new PropertiesContext();
		pc.setEncoding(project.getEncoding());
		pc.setLocations(Arrays.asList("classpath:ole-fs.properties"));

		ProjectProperties pp = new ProjectProperties();
		pp.setProject(project);
		pp.setPropertiesContext(pc);
		return pp;
	}

	@Bean(initMethod = "execute")
	public Executable springExecutable() {
		String skip = SpringUtils.getProperty(env, "db.reset.skip", "false");

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

		SpringContextLoaderExecutable scle = new SpringContextLoaderExecutable();
		scle.setService(new DefaultSpringService());
		scle.setContext(context);
		scle.setSkip(new Boolean(skip));
		return scle;
	}

	@Bean
	public PropertySource<?> springPropertySource() {
		String name = "springPropertySource";

		// Combine project properties into a list where the "last one in wins"
		List<ProjectProperties> pps = new ArrayList<ProjectProperties>();
		pps.add(jdbcPropertiesConfig.jdbcProjectProperties());
		pps.add(oleProjectProperties());

		// Load them from disk
		Properties source = PropertyUtils.load(pps);

		// Override with system/environment properties
		source.putAll(PropertyUtils.getGlobalProperties());

		//
		boolean resolve = new Boolean(SpringUtils.getProperty(env, "properties.resolve", "true"));
		if (resolve) {
			ResolvePlaceholdersProcessor rpp = new ResolvePlaceholdersProcessor();
			rpp.process(source);
		}

		String encryptionMode = SpringUtils.getProperty(env, "properties.decrypt", EncryptionMode.NONE.name());
		EncryptionMode mode = EncryptionMode.valueOf(encryptionMode);
		boolean decrypt = EncryptionMode.DECRYPT.equals(mode);
		if (decrypt) {
			String password = SpringUtils.getProperty(env, "properties.enc.password");
			String strength = SpringUtils.getProperty(env, "properties.enc.strength", EncryptionStrength.BASIC.name());
			EncryptionStrength es = EncryptionStrength.valueOf(strength);
			TextEncryptor encryptor = EncUtils.getTextEncryptor(es, password);
			PropertyUtils.decrypt(source, encryptor);
		}

		// Return a PropertySource backed by the properties
		return new PropertiesPropertySource(name, source);
	}
}
