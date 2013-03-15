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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

@Configuration
public class MetaInfProjectPropertiesConfig {

	private static final String FS = File.separator;

	/**
	 * spring-maven-plugin auto-wires Maven properties by default
	 */
	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Autowired
	Environment env;

	@Bean(initMethod = "execute")
	public Executable storePropertiesExecutable() {
		// Extract environment property values
		String encoding = env.getRequiredProperty("project.encoding");
		String includesCSV = env.getRequiredProperty("project.metainf.includes");
		String excludesCSV = env.getRequiredProperty("project.metainf.excludes");

		// Make sure we are configured right
		Assert.hasText(encoding);
		Assert.hasText(includesCSV);
		Assert.hasText(excludesCSV);

		// Convert the lists to CSV
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);

		// Get a handle to the output file
		String filename = getOutputFilename(env);
		File outputFile = new File(filename);

		// Setup the executable
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setEncoding(encoding);
		spe.setOutputFile(outputFile);
		spe.setProperties(mavenProperties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);
		return spe;
	}

	/**
	 * Use Maven properties to build a filename
	 */
	protected String getOutputFilename(Environment env) {
		StringBuilder sb = new StringBuilder();
		sb.append(env.getRequiredProperty("project.build.outputDirectory"));
		sb.append(FS);
		sb.append("META-INF");
		sb.append(FS);
		sb.append(env.getRequiredProperty("project.groupId.path"));
		sb.append(FS);
		sb.append(env.getRequiredProperty("project.artifactId"));
		sb.append(".properties");
		return sb.toString();
	}
}
