/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins.context;

import java.io.File;

public class JobContext {
	String name;
	String type;
	String template;
	File workingDir;
	File localFile;
	String timestampFormat;
	String resolvedContent;
	String unresolvedContent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public File getLocalFile() {
		return localFile;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}

	public String getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(String timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	public String getResolvedContent() {
		return resolvedContent;
	}

	public void setResolvedContent(String resolvedContent) {
		this.resolvedContent = resolvedContent;
	}

	public String getUnresolvedContent() {
		return unresolvedContent;
	}

	public void setUnresolvedContent(String unresolvedContent) {
		this.unresolvedContent = unresolvedContent;
	}
}
