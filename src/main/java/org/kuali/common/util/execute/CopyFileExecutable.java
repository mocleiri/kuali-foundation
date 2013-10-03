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
package org.kuali.common.util.execute;

import java.io.File;

import org.kuali.common.util.Assert;

public class CopyFileExecutable implements Executable {

	boolean skip;
	CopyFileRequest request;
	CopyFileResult result;

	public CopyFileExecutable() {
		this(null);
	}

	public CopyFileExecutable(File source, File destination) {
		this(new CopyFileRequest(source, destination));
	}

	public CopyFileExecutable(CopyFileRequest request) {
		super();
		this.request = request;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(request, "request is null");

		CopyFilesExecutable exec = new CopyFilesExecutable(request);
		exec.execute();
		this.result = exec.getResults().get(0);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
