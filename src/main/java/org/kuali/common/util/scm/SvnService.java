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
package org.kuali.common.util.scm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.service.DefaultExecService;

public class SvnService extends DefaultExecService implements ScmService {

	private static final String SVN = "svn";

	@Override
	public void version() {
		executeAndValidate(SVN, Arrays.asList("--version"));
	}

	@Override
	public void add(List<File> paths) {
		if (CollectionUtils.isEmpty(paths)) {
			// Nothing to do
			return;
		}
		String command = "add";
		List<String> cpaths = LocationUtils.getCanonicalPaths(paths);
		List<String> options = Arrays.asList("--force", "--parents", "--depth", "infinity");

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(cpaths);
		arguments.addAll(options);

		executeAndValidate(SVN, arguments);
	}

	@Override
	public void delete(List<File> paths) {
		if (CollectionUtils.isEmpty(paths)) {
			// Nothing to do
			return;
		}
		String command = "delete";
		List<String> cpaths = LocationUtils.getCanonicalPaths(paths);
		List<String> options = Arrays.asList("--force");

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(cpaths);
		arguments.addAll(options);

		executeAndValidate(SVN, arguments);
	}

	@Override
	public void commit(List<File> paths, String message) {
		if (CollectionUtils.isEmpty(paths)) {
			// Nothing to do
			return;
		}
		Assert.noBlanks("Commit message is blank", message);
		String command = "commit";
		List<String> cpaths = LocationUtils.getCanonicalPaths(paths);
		List<String> options = Arrays.asList("--depth", "infinity", "--message", message);

		List<String> arguments = new ArrayList<String>();
		arguments.add(command);
		arguments.addAll(cpaths);
		arguments.addAll(options);

		executeAndValidate(SVN, arguments);
	}

}
