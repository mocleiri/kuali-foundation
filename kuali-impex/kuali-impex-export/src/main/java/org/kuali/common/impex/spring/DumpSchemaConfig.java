/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.spring;

import java.io.File;
import java.util.List;

import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.impex.schema.execute.DumpSchemaExecutable;
import org.kuali.common.impex.schema.execute.DumpSchemaRequest;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ExportServicesConfig.class })
public class DumpSchemaConfig {

	private static final String FILE_KEY = "impex.dump.schema.file";
	private static final String INCLUDES_KEY = "impex.dump.schema.includes";
	private static final String EXCLUDES_KEY = "impex.dump.schema.excludes";
	private static final String SKIP_KEY = "impex.dump.schema.skip";
	private static final String RELATIVE_DIR_KEY = "impex.dump.schema.dir.relative";
	private static final String LOG_EXCLUDES_KEY = "impex.dump.schema.log.excludes";

	@Autowired
	Environment env;

	@Autowired
	ExportServicesConfig exportServicesConfig;

	@Bean
	public DumpSchemaExecutable dumpSchemaExecutable() {

		DumpSchemaService service = exportServicesConfig.exportDumpSchemaService();

		// Extract some context from the environment
		File outputFile = SpringUtils.getFile(env, FILE_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, outputFile);
		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, false);
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, INCLUDES_KEY, DumpConstants.DEFAULT_REGEX_INCLUDE);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, EXCLUDES_KEY, DumpConstants.DEFAULT_REGEX_EXCLUDE);
		boolean logExcludedSchemaObjects = SpringUtils.getBoolean(env, LOG_EXCLUDES_KEY, false);

		// Setup the request
		DumpSchemaRequest request = new DumpSchemaRequest();
		request.setOutputFile(outputFile);
		request.setExcludes(excludes);
		request.setIncludes(includes);
		request.setLogExcludedSchemaObjects(logExcludedSchemaObjects);
		request.setRelativeDir(relativeDir);

		// Setup the executable
		return new DumpSchemaExecutable(service, skip, request);
	}

}
