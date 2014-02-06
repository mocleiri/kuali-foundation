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
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @deprecated
 */
@Deprecated
@Configuration
public class MetaInfMpxConfig {

	private static final String DEFAULT_INCLUDE_PATTERN = "**/*.mpx";
	private static final String DEFAULT_OUTPUT_FILE = "${project.build.outputDirectory}/META-INF/${project.groupId.base.path}/${project.artifactId}/data.resources";
	private static final String INCLUDES_KEY = "impex.metainf.includes";
	private static final String OUTPUT_FILE_KEY = "impex.metainf.includes";
	private static final String BUILD_OUTPUT_DIR_KEY = "project.build.outputDirectory";

	@Autowired
	Environment env;

	@Bean
	public Executable mpxMetaInfExecutable() {

		// Extract the CSV include patterns and convert to a list
		String csv = SpringUtils.getProperty(env, INCLUDES_KEY, DEFAULT_INCLUDE_PATTERN);
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(csv);

		// This is the base directory to scan
		File buildOutputDir = new File(SpringUtils.getProperty(env, BUILD_OUTPUT_DIR_KEY));

		// Output file contains one line of text for each file that gets located
		// Each line is an entry similar to this "classpath:<groupId>/<artifactId>/<tablename>.mpx"
		File outputFile = new File(SpringUtils.getProperty(env, OUTPUT_FILE_KEY, DEFAULT_OUTPUT_FILE));

		// Setup the context
		org.kuali.common.util.metainf.MetaInfContext context = new org.kuali.common.util.metainf.MetaInfContext();
		context.setBaseDir(buildOutputDir);
		context.setOutputFile(outputFile);
		context.setIncludes(includes);

		// Setup and return an executable
		org.kuali.common.util.execute.MetaInfExecutable exec = new org.kuali.common.util.execute.MetaInfExecutable();
		exec.setContexts(Arrays.asList(context));
		return exec;
	}

}
