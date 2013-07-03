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
import org.kuali.common.util.MetaInfContext;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.MetaInfExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MetaInfMpxConfig {

	public static final String DEFAULT_INCLUDE_PATTERN = "**/${project.groupId.base.path}/${project.artifactId}/*.mpx";
	public static final String DEFAULT_OUTPUT_FILE = "${project.build.outputDirectory}/META-INF/${project.groupId.base.path}/${project.artifactId}/data.resources";

	@Autowired
	Environment env;

	@Bean
	public Executable mpxMetaInfExecutable() {

		// Extract the CSV include patterns and convert to a list
		String csv = SpringUtils.getProperty(env, "impex.metainf.includes", DEFAULT_INCLUDE_PATTERN);
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(csv);

		// This is the base directory to scan
		File baseDir = new File(SpringUtils.getProperty(env, "project.build.outputDirectory"));

		// Output file contains one line of text for each file that gets located
		// Each line is an entry similar to this "classpath:MYCONTENT.mpx"
		File outputFile = new File(SpringUtils.getProperty(env, "impex.metainf.outputFile", DEFAULT_OUTPUT_FILE));

		// Setup the context
		MetaInfContext context = new MetaInfContext();
		context.setBaseDir(baseDir);
		context.setOutputFile(outputFile);
		context.setIncludes(includes);

		// Setup and return an executable
		MetaInfExecutable mie = new MetaInfExecutable();
		mie.setContexts(Arrays.asList(context));
		return mie;
	}

}
