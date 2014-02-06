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
 * @deprecated
 */
@Deprecated
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
		return PropertySourceUtils.getAllEnumerableProperties(env);
	}

	@Bean
	public Executable storePropertiesExecutablesExectuable() {

		// Extract property values from the environment
		String encoding = SpringUtils.getProperty(env, "project.encoding");

		// Encoding must be provided
		Assert.hasText(encoding);

		// Setup includes / excludes
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, "project.metainf.includes");
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, "project.metainf.excludes");

		// Get the list of all properties spring knows about
		Properties properties = springProperties();

		// Setup the regular properties file executable
		StorePropertiesExecutable spe = new StorePropertiesExecutable();
		spe.setSkip(SpringUtils.getBoolean(env, "project.metainf.skip", false));
		spe.setSkipIfExists(SpringUtils.getBoolean(env, "project.metainf.skipIfExists", true));
		spe.setSkipIfEqual(SpringUtils.getBoolean(env, "project.metainf.skipIfEqual", true));
		spe.setEncoding(encoding);
		spe.setOutputFile(outputFile);
		spe.setProperties(properties);
		spe.setIncludes(includes);
		spe.setExcludes(excludes);

		// Setup the Rice style properties file executable
		StoreRicePropertiesExecutable srpe = new StoreRicePropertiesExecutable();
		spe.setSkip(SpringUtils.getBoolean(env, "project.metainf.skip", false));
		spe.setSkipIfExists(SpringUtils.getBoolean(env, "project.metainf.skipIfExists", true));
		spe.setSkipIfEqual(SpringUtils.getBoolean(env, "project.metainf.skipIfEqual", true));
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
