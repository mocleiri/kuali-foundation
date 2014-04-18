/**
 * Copyright 2011-2014 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.maven.plugins.jenkins.context.ProcessResult;

public abstract class ExecutableHelper {

	ProcessHelper helper = new ProcessHelper();

	public abstract String getExecutable();

	public List<String> buildArgumentListFromMap(Map<String, String> options) {
		List<String> list = new ArrayList<String>();
		for (String key : options.keySet()) {
			String value = options.get(key);
			list.add(key.startsWith("-") ? key : "-" + key);
			if (!StringUtils.isBlank(value)) {
				list.add(options.get(key));
			}
		}
		return list;
	}

	public ProcessResult execute(Map<String, String> options, List<String> args, String input) {
		String[] arr = {};
		List<String> list = buildArgumentListFromMap(options);
		list.addAll(args);
		String[] sArgs = list.toArray(arr);
		return helper.execute(getExecutable(), sArgs, input);
	}
}
