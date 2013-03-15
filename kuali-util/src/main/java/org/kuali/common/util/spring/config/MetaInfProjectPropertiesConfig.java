package org.kuali.common.util.spring.config;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.StorePropertiesExecutable;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.Assert;

@Configuration
public class MetaInfProjectPropertiesConfig {

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value(value = "${project.metainf.includes}")
	String includesCSV;

	@Value(value = "${project.metainf.excludes}")
	String excludesCSV;

	@Value(value = "${project.build.outputDirectory}/META-INF/${project.orgId.path}/${project.artifactId}.properties")
	File outputFile;

	@Value(value = "${project.encoding}")
	String encoding;

	@Bean(initMethod = "execute")
	public Executable storePropertiesExecutable() {
		// Make sure we are configured right
		Assert.hasText(encoding);
		Assert.hasText(includesCSV);
		Assert.hasText(excludesCSV);
		Assert.notNull(outputFile);

		// Convert the lists to CSV
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);

		// Setup the executable
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setEncoding(encoding);
		spe.setOutputFile(outputFile);
		spe.setProperties(mavenProperties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);
		return spe;
	}
}
