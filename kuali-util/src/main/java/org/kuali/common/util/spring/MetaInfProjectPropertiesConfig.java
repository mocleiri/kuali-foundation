package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.StorePropertiesExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;

@Configuration
public class MetaInfProjectPropertiesConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${project.build.outputDirectory}/META-INF/${project.groupId.path}/${project.artifactId}.properties")
	File outputFile;

	@Bean
	public Properties springProperties() {
		return SpringUtils.getAllEnumerableProperties(env);
	}

	@Bean(initMethod = "execute")
	public Executable storePropertiesExecutable() {

		// Extract property values from the environment
		String encoding = SpringUtils.getProperty(env, "project.encoding");
		String includesCSV = SpringUtils.getProperty(env, "project.metainf.includes");
		String excludesCSV = SpringUtils.getProperty(env, "project.metainf.excludes");

		// Make sure we are configured right
		Assert.hasText(encoding);
		Assert.hasText(includesCSV);
		Assert.hasText(excludesCSV);

		// Convert the lists to CSV
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);

		// Get the list of all properties spring knows about
		Properties properties = springProperties();

		// Setup the executable
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setEncoding(encoding);
		spe.setOutputFile(outputFile);
		spe.setProperties(properties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);
		return spe;
	}

}
