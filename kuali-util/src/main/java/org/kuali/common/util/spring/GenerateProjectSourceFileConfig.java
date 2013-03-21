package org.kuali.common.util.spring;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.service.ScmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PropertyPlaceholderHelper;

@Configuration
public class GenerateProjectSourceFileConfig {

	private static final Logger logger = LoggerFactory.getLogger(GenerateProjectSourceFileConfig.class);

	private static final String FS = File.separator;

	@Autowired
	@Qualifier(Constants.DEFAULT_MAVEN_PROPERTIES_BEAN_NAME)
	Properties mavenProperties;

	@Bean
	public Object doFile() {
		try {
			String template = ProjectUtils.getJavaSourceFileTemplate();
			String encoding = mavenProperties.getProperty("project.encoding");
			String artifactId = mavenProperties.getProperty("project.artifactId");
			String classname = getJavaClassName(artifactId);
			String scmUrl = mavenProperties.getProperty("project.scm.developerConnection");
			ScmServiceFactoryBean ssfb = new ScmServiceFactoryBean();
			ssfb.setUrl(scmUrl);
			ScmService service = ssfb.getObject();
			mavenProperties.setProperty("project.artifactId.classname", classname);
			PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
			String source = pph.replacePlaceholders(template, mavenProperties);
			String filename = getFilename(mavenProperties);
			File outputFile = new File(filename);
			boolean existing = LocationUtils.exists(outputFile);
			boolean identical = existingIsIdentical(outputFile, source, encoding);
			if (identical) {
				return null;
			}
			logger.info("Creating [{}]", LocationUtils.getCanonicalPath(outputFile));
			FileUtils.write(outputFile, source);
			if (!existing) {
				service.add(Arrays.asList(outputFile));
			}
			service.commit(Arrays.asList(outputFile), "Automatically generated java source code - Maven GAV");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		return null;
	}

	protected boolean existingIsIdentical(File file, String newContent, String encoding) {
		if (!LocationUtils.exists(file)) {
			return false;
		}
		String cpath = LocationUtils.getCanonicalPath(file);
		String oldContent = LocationUtils.toString(cpath, encoding);
		return StringUtils.equals(newContent, oldContent);
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
