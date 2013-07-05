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

import org.kuali.common.impex.schema.DumpSchemaExecutable;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DumpSchemaConfig {

	// Required (no default value)
	public static final String FILE_KEY = "impex.dump.schema.file";

	// Optional
	public static final String SERVICE_KEY = "impex.dump.schema.service";
	public static final String SKIP_KEY = "impex.dump.schema.skip";
	public static final String RELATIVE_DIR_KEY = "impex.dump.schema.dir.relative";

	@Autowired
	Environment env;

	@Bean
	public DumpSchemaExecutable dumpSchemaExecutable() {

		// Extract some context from the environment
		File outputFile = SpringUtils.getFile(env, FILE_KEY);
		File relativeDir = SpringUtils.getFile(env, RELATIVE_DIR_KEY, outputFile);
		DumpSchemaService service = SpringUtils.getInstance(env, SERVICE_KEY, DumpSchemaExecutable.DEFAULT_EXPORT_SCHEMA_SERVICE.getClass());
		boolean skip = SpringUtils.getBoolean(env, SKIP_KEY, false);

		// Configure an executable
		DumpSchemaExecutable exec = new DumpSchemaExecutable();
		exec.setOutputFile(outputFile);
		exec.setRelativeDir(relativeDir);
		exec.setService(service);
		exec.setSkip(skip);
		return exec;
	}

}
