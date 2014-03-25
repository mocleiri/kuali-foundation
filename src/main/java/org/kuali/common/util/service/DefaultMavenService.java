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
package org.kuali.common.util.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;

public class DefaultMavenService extends DefaultExecService implements MavenService {

	@Override
	public void execute(MavenContext context) {

		// Update options with MavenContext attributes
		handleOptions(context);

		// Convert options/goals/phases into an args list
		List<String> args = getArgs(context);

		// Create an execution context
		DefaultExecContext dec = new DefaultExecContext();
		dec.setExecutable(context.getExecutable());
		dec.setWorkingDirectory(context.getWorkingDir());
		dec.setArgs(args);

		// TODO Re-factor things so only MAVEN_OPTS gets inherited instead of everything
		if (context.isInheritMavenOpts()) {
			dec.setAddSystemEnvironment(true);
		}

		// Execute Maven making sure we get 0 as a return value
		executeAndValidate(dec);
	}

	@Override
	public void execute(File workingDir, List<String> options, List<String> goals, List<String> phases) {
		MavenContext context = new MavenContext();
		context.setWorkingDir(workingDir);
		context.setOptions(options);
		context.setGoals(goals);
		context.setPhases(phases);
		execute(context);
	}

	protected List<String> getOptions(MavenContext context) {
		List<String> options = new ArrayList<String>();
		if (context.isBatchMode()) {
			options.add("--batch-mode");
		}
		if (context.isDebug()) {
			options.add("--debug");
		}
		if (context.isErrors()) {
			options.add("--errors");
		}
		if (context.isOffline()) {
			options.add("--offline");
		}
		if (context.isQuiet()) {
			options.add("--quiet");
		}
		if (context.getPom() != null) {
			String cpath = LocationUtils.getCanonicalPath(context.getPom());
			options.add("--file");
			options.add(cpath);
		}
		if (!CollectionUtils.isEmpty(context.getProfiles())) {
			String csv = CollectionUtils.getCSV(context.getProfiles());
			options.add("--activate-profiles");
			options.add(csv);
		}
		return options;
	}

	protected void handleOptions(MavenContext context) {
		List<String> options = getOptions(context);
		if (context.getOptions() == null) {
			context.setOptions(options);
		} else {
			context.getOptions().addAll(options);
		}
	}

	protected List<String> getArgs(MavenContext context) {
		List<String> args = new ArrayList<String>();
		if (!CollectionUtils.isEmpty(context.getOptions())) {
			args.addAll(context.getOptions());
		}
		if (!CollectionUtils.isEmpty(context.getGoals())) {
			args.addAll(context.getGoals());
		}
		if (!CollectionUtils.isEmpty(context.getPhases())) {
			args.addAll(context.getPhases());
		}
		if (!CollectionUtils.isEmpty(context.getPassThroughPropertyKeys())) {
			Properties p = getPassThroughProperties(context);
			List<String> keys = PropertyUtils.getSortedKeys(p);
			for (String key : keys) {
				String value = p.getProperty(key);
				String arg = "-D" + key + "=" + value;
				args.add(arg);
			}
		}
		return args;
	}

	protected Properties getPassThroughProperties(MavenContext context) {
		List<String> keys = context.getPassThroughPropertyKeys();
		Properties properties = new Properties();
		Collections.sort(keys);
		Properties global = PropertyUtils.getGlobalProperties(context.getProperties());
		for (String key : keys) {
			String value = global.getProperty(key);
			if (!StringUtils.isBlank(value)) {
				properties.setProperty(key, value);
			}
		}
		return properties;
	}

}
