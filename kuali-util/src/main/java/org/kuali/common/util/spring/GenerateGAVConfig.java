/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.spring;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.PropertyPlaceholderHelper;

@Configuration
public class GenerateGAVConfig {

	private static final Logger logger = LoggerFactory.getLogger(GenerateGAVConfig.class);

	private static final String FS = File.separator;

	@Autowired
	Environment env;

	@Bean
	public Object doGAV() {
		// Skip everything
		boolean skip = SpringUtils.isTrue(env, "project.gav.skip");
		// Validate only, don't create the file or commit it
		boolean validate = SpringUtils.isTrue(env, "project.gav.validate");
		// Create (or update) the file
		boolean update = SpringUtils.isTrue(env, "project.gav.update");
		// Commit the file to SCM
		boolean commit = SpringUtils.isTrue(env, "project.gav.commit");
		if (skip) {
			logger.info("Skipping GAV check");
			return null;
		}
		String template = ProjectUtils.getJavaSourceFileTemplate();
		String filename = getFilename(env);
		File outputFile = new File(filename);
		String encoding = SpringUtils.getProperty(env, "project.encoding");
		Properties p = getPlaceholderProperties(env);
		PropertyPlaceholderHelper pph = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
		String sourceCode = pph.replacePlaceholders(template, p);
		boolean existing = LocationUtils.exists(outputFile);
		boolean identical = existingIsIdentical(outputFile, sourceCode, encoding);

		// Validate everything matches up, but take no further action
		if (validate) {
			if (identical) {
				logger.info("Verified GAV - [{}]", LocationUtils.getCanonicalPath(outputFile));
				return null;
			} else {
				throw new IllegalStateException("GAV information is out of sync [" + LocationUtils.getCanonicalPath(outputFile) + "]");
			}
		}

		// Nothing to update or commit if this is true
		if (identical) {
			return null;
		}

		// Create (or update) the .java file
		if (update) {
			String action = existing ? "Updating" : "Creating";
			logger.info("{} [{}]", action, LocationUtils.getCanonicalPath(outputFile));
			write(outputFile, sourceCode);
		}

		// Commit the .java file to SCM
		if (commit) {
			ScmService service = getScmService(env);
			if (!existing) {
				service.add(Arrays.asList(outputFile));
			}
			logger.info("Committing [{}]", LocationUtils.getCanonicalPath(outputFile));
			service.commit(Arrays.asList(outputFile), "Automatically generated java source code - Maven GAV");
		}
		return null;
	}

	protected ScmService getScmService(Environment env) {
		String scmUrl = SpringUtils.getProperty(env, "project.scm.developerConnection");
		ScmServiceFactoryBean ssfb = new ScmServiceFactoryBean();
		ssfb.setUrl(scmUrl);
		try {
			return ssfb.getObject();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected void write(File file, String content) {
		try {
			FileUtils.write(file, content);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	protected boolean isSkip(Environment env) {
		return SpringUtils.isTrue(env, "project.gav.skip");
	}

	protected Properties getPlaceholderProperties(Environment env) {
		String classname = getJavaClassName(SpringUtils.getProperty(env, "project.artifactId"));
		List<String> keys = Arrays.asList("project.groupId", "project.artifactId", "project.version");
		Properties p = new Properties();
		for (String key : keys) {
			p.setProperty(key, SpringUtils.getProperty(env, key));
		}
		p.setProperty("project.artifactId.classname", classname);
		return p;
	}

	protected boolean existingIsIdentical(File file, String newContent, String encoding) {
		if (!LocationUtils.exists(file)) {
			return false;
		}
		String cpath = LocationUtils.getCanonicalPath(file);
		String oldContent = LocationUtils.toString(cpath, encoding);
		return StringUtils.equals(newContent, oldContent);
	}

	protected String getFilename(Environment env) {
		String srcDir = SpringUtils.getProperty(env, "project.build.sourceDirectory");
		String groupId = SpringUtils.getProperty(env, "project.groupId");
		String artifactId = SpringUtils.getProperty(env, "project.artifactId");
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
		sb.append("GAV");
		return sb.toString();
	}

}
