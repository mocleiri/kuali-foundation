/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.mojo.morph;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class VersionTableMorpher extends Morpher {
	private static final Log log = LogFactory.getLog(VersionTableMorpher.class);

	String projectVersion;

	public VersionTableMorpher() {
		this(null, null);
	}

	public VersionTableMorpher(MorphRequest morphRequest, String artifactId) {
		super(morphRequest, artifactId);
	}

	protected String getMorphedContents(String contents) {
		log.debug("contents=" + contents);
		String oldVersion = StringUtils.substringBetween(contents, "VERSION=\"", "\"");
		String searchString = "VERSION=\"" + oldVersion + "\"";
		String replacement = "VERSION=\"" + getProjectVersion() + "\"";
		String s = StringUtils.replace(contents, searchString, replacement);
		return s;
	}

	/**
	 * Return true if we need to morph this file
	 */
	protected boolean isMorphNeeded(String contents) {
		return true;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

}
