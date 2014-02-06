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

import org.kuali.common.util.execute.ConvertTableListingTextFileExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConvertTableListingTextFileConfig {

	@Autowired
	Environment env;

	@Bean
	public ConvertTableListingTextFileExecutable convertTableListingTextFileExecutable() {

		String artifactId = SpringUtils.getProperty(env, "impex.txt.artifactId");
		File outputDir = SpringUtils.getFile(env, "project.build.outputDirectory");
		boolean skip = SpringUtils.getBoolean(env, "impex.txt.skip", false);

		ConvertTableListingTextFileExecutable e = new ConvertTableListingTextFileExecutable();
		e.setArtifactId(artifactId);
		e.setOutputDir(outputDir);
		e.setSkip(skip);
		return e;
	}

}
