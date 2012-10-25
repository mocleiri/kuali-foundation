/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.codehaus.mojo.properties;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * @author <a href="mailto:zarars@gmail.com">Zarar Siddiqi</a>
 * @version $Id: AbstractWritePropertiesMojo.java 8861 2009-01-21 15:35:38Z pgier $
 */
public abstract class AbstractWritePropertiesMojo extends AbstractMojo {

	/**
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	MavenProject project;

	/**
	 * The file that properties will be written to
	 *
	 * @parameter expression="${properties.outputFile}" default-value="${project.build.directory}/properties/project.properties";
	 * @required
	 */
	File outputFile;

	/**
	 * Either <code>NORMAL</code> or <code>ENVIRONMENT_VARIABLE</code>. When set to <code>ENVIRONMENT_VARIABLE</code> the keys in the
	 * properties file will all be upper case with periods replaced by underscores.
	 *
	 * @parameter expression="${properties.outputStyle}" default-value="NORMAL";
	 * @required
	 */
	OutputStyle outputStyle;

	protected void writeProperties(File file, Properties properties, OutputStyle outputStyle) throws MojoExecutionException {
		Properties formatted = getProperties(properties, outputStyle);
		Properties sorted = getSortedProperties(formatted);
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(file);
			sorted.store(out, "Created by the properties-maven-plugin");
		} catch (IOException e) {
			throw new MojoExecutionException("Error creating properties file", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected SortedProperties getSortedProperties(Properties properties) {
		SortedProperties sp = new SortedProperties();
		sp.putAll(properties);
		return sp;
	}

	protected Properties getProperties(Properties properties, OutputStyle style) {
		switch (style) {
		case NORMAL:
			return properties;
		case ENVIRONMENT_VARIABLE:
			return getEnvironmentVariableProperties(properties);
		default:
			throw new IllegalArgumentException(outputStyle + " is unknown");
		}
	}

	protected Properties getEnvironmentVariableProperties(Properties properties) {
		List<String> keys = new ArrayList<String>(properties.stringPropertyNames());
		Collections.sort(keys);
		Properties newProperties = new Properties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			String newKey = key.toUpperCase().replace(".", "_");
			newProperties.setProperty(newKey, value);
		}
		return newProperties;
	}
}
