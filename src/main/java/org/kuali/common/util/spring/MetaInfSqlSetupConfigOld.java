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
public class MetaInfSqlSetupConfigOld {

	@Autowired
	Environment env;

	@Bean
	public Executable metaInfExecutable() {
		List<org.kuali.common.util.metainf.MetaInfContext> contexts = new ArrayList<org.kuali.common.util.metainf.MetaInfContext>();
		contexts.add(getMetaInfContext("metainf.output.schema", "metainf.include.schema"));
		contexts.add(getMetaInfContext("metainf.output.data", "metainf.include.data"));
		contexts.add(getMetaInfContext("metainf.output.constraints", "metainf.include.constraints"));
		contexts.add(getMetaInfContext("metainf.output.other", "metainf.include.other"));

		org.kuali.common.util.execute.MetaInfExecutable mie = new org.kuali.common.util.execute.MetaInfExecutable();
		mie.setSkip(SpringUtils.getBoolean(env, "metainf.sql.skip", false));
		mie.setContexts(contexts);
		return mie;

	}

	protected org.kuali.common.util.metainf.MetaInfContext getMetaInfContext(String outputFileKey, String includesKey) {
		String csv = SpringUtils.getProperty(env, includesKey);
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(csv);
		File outputFile = new File(SpringUtils.getProperty(env, outputFileKey));
		File baseDir = new File(SpringUtils.getProperty(env, "project.build.outputDirectory"));

		org.kuali.common.util.metainf.MetaInfContext context = new org.kuali.common.util.metainf.MetaInfContext();
		context.setBaseDir(baseDir);
		context.setOutputFile(outputFile);
		context.setIncludes(includes);
		return context;
	}
}
