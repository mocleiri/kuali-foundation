package org.kuali.common.util.spring;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PropertyPlaceholderHelper;

@Configuration
public class GenerateProjectSourceFileConfig {

	private static final String FS = File.separator;

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public Object doFile() {
		String template = ProjectUtils.getJavaSourceFileTemplate();
		PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		String source = pph.replacePlaceholders(template, mavenProperties);
		String filename = getFilename(mavenProperties);
		File outputFile = new File(filename);
		try {
			FileUtils.write(outputFile, source);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return null;
	}

	protected String getFilename(Properties p) {
		String srcDir = p.getProperty("project.build.sourceDirectory");
		String groupId = p.getProperty("project.groupId");
		String artifactId = p.getProperty("project.artifactId");
		String classname = getJavaClassName(artifactId);

		StringBuilder sb = new StringBuilder();
		sb.append(srcDir);
		sb.append(FS);
		sb.append(Str.getPath(groupId));
		sb.append(FS);
		sb.append(classname);
		sb.append(".java");
		return sb.toString();
	}

	protected String getJavaClassName(String artifactId) {
		String[] tokens = StringUtils.split(artifactId, "-");
		StringBuilder sb = new StringBuilder();
		for (String token : tokens) {
			String capitalized = StringUtils.capitalizeFirstLetter(token);
			sb.append(capitalized);
		}
		return sb.toString();
	}

}
