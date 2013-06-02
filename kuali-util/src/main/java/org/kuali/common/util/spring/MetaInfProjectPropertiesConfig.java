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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * Create project.properties and embed it inside META-INF for jar's/war's
 */
@Configuration
public class MetaInfProjectPropertiesConfig {

	@Autowired
	ConfigurableEnvironment env;

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

		// Convert the lists to CSV
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(includesCSV);
		List<String> excludes = CollectionUtils.getTrimmedListFromCSV(excludesCSV);

		// Get the list of all properties spring knows about
		Properties properties = springProperties();

		// Configure a helper that fails on un-resolvable placeholders
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}", ":", false);
		String outputFilename = helper.replacePlaceholders(Constants.PROJECT_PROPERTIES_OUTPUTFILE, properties);
		String riceOutputFilename = helper.replacePlaceholders(Constants.RICE_PROJECT_PROPERTIES_OUTPUTFILE, properties);

		System.out.println("outputFilename=" + outputFilename);
		System.out.println("riceOutputFilename=" + riceOutputFilename);

		// Setup the regular properties file executable
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setEncoding(encoding);
		spe.setOutputFile(new File(outputFilename));
		spe.setProperties(properties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);

		// Setup the Rice style properties file executable
		StoreRicePropertiesExecutable srpe = new StoreRicePropertiesExecutable();
		spe.setEncoding(encoding);
		spe.setOutputFile(new File(riceOutputFilename));
		spe.setProperties(properties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);

		// Create an executables list
		List<Executable> executables = new ArrayList<Executable>();
		// executables.add(spe);
		executables.add(srpe);

		// Return an executable that executes the list
		return new ExecutablesExecutable(executables);
	}

}
