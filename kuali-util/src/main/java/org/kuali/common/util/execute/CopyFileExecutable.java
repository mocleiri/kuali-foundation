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

public class CopyFileExecutable implements Executable {

	boolean skip;
	File source;
	File destination;

	public CopyFileExecutable() {
		this(null, null, false);
	}

	public CopyFileExecutable(File source, File destination, boolean skip) {
		super();
		this.skip = skip;
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		CopyFileRequest request = new CopyFileRequest(source, destination);
		CopyFilesExecutable exec = new CopyFilesExecutable(request);
		exec.execute();
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public File getDestination() {
		return destination;
	}

	public void setDestination(File destination) {
		this.destination = destination;
	}

}
