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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.execute.StorePropertiesExecutable;
import org.kuali.common.util.execute.StoreRicePropertiesExecutable;
import org.kuali.common.util.property.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;

/**
 * Create project.properties and embed it inside META-INF for jar's/war's
 */
@Configuration
public class MetaInfProjectPropertiesSetupConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public static PropertySourcesPlaceholderConfigurer pspc() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value(Constants.PROJECT_PROPERTIES_OUTPUTFILE)
	File outputFile;

	@Value(Constants.RICE_PROJECT_PROPERTIES_OUTPUTFILE)
	File riceOutputFile;

	@Bean
	public Properties springProperties() {
		return SpringUtils.getAllEnumerableProperties(env);
	}

	@Bean
	public Executable storePropertiesExecutablesExectuable() {

		// Extract property values from the environment
		String encoding = SpringUtils.getProperty(env, "project.encoding");
		String includesCSV = SpringUtils.getProperty(env, "project.metainf.includes");
		String excludesCSV = SpringUtils.getProperty(env, "project.metainf.excludes");

		// Make sure we are configured right
		Assert.hasText(encoding);

		// Convert the lists to CSV
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);

		// Get the list of all properties spring knows about
		Properties properties = springProperties();

		// Setup the regular properties file executable
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setSkip(SpringUtils.getBoolean(env, "project.properties.skip", false));
		spe.setEncoding(encoding);
		spe.setOutputFile(outputFile);
		spe.setProperties(properties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);

		// Setup the Rice style properties file executable
		StoreRicePropertiesExecutable srpe = new StoreRicePropertiesExecutable();
		srpe.setSkip(SpringUtils.getBoolean(env, "project.properties.rice.skip", false));
		srpe.setEncoding(encoding);
		srpe.setOutputFile(riceOutputFile);
		srpe.setProperties(properties);
		srpe.setIncludes(includes);
		srpe.setExcludes(excludes);

		// Create an executables list
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(spe);
		executables.add(srpe);

		// Return an executable that executes the list
		return new ExecutablesExecutable(executables);
	}

}
