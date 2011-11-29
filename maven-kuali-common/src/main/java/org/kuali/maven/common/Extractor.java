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
package org.kuali.maven.common;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.model.Scm;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

/**
 * Extracts information contained in a pom and exposes it as project properties
 * 
 * eg major version, scm type, scm url
 * 
 */
public class Extractor {

	public void handleMajorVersion(AbstractMojo mojo, MavenProject project, String property) {

		String majorVersion = getMajorVersion(project.getVersion());
		if (!StringUtils.isEmpty(majorVersion)) {
			project.getProperties().setProperty(property, majorVersion);
			mojo.getLog().debug("Setting project property: " + property + "=" + majorVersion);
		} else {
			mojo.getLog().debug("Major version could not be determined");
		}
	}

	public void handleScmUrl(AbstractMojo mojo, MavenProject project, String property) {
		String scmUrl = getScmUrl(project.getScm());
		if (!StringUtils.isEmpty(scmUrl)) {
			project.getProperties().setProperty(property, scmUrl);
			mojo.getLog().debug("Setting project property: " + property + "=" + scmUrl);
		} else {
			mojo.getLog().debug("scm url could not be determined");
		}
	}

	public void handleScmType(AbstractMojo mojo, MavenProject project, String property) {
		String scmType = getScmType(project.getScm());
		if (!StringUtils.isEmpty(scmType)) {
			project.getProperties().setProperty(property, scmType);
			mojo.getLog().debug("Setting project property: " + property + "=" + scmType);
		} else {
			mojo.getLog().debug("scm type could not be determined");
		}
	}

	public String getScmUrl(Scm scm) {
		String devCon = scm.getDeveloperConnection();
		String con = scm.getDeveloperConnection();
		String scmUrl = StringUtils.isEmpty(devCon) ? con : devCon;
		if (StringUtils.isEmpty(scmUrl)) {
			return null;
		}
		String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(scmUrl, ":");
		if (tokens == null || tokens.length < 3) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < tokens.length; i++) {
			if (i != 2) {
				sb.append(":");
			}
			sb.append(tokens[i]);
		}
		return sb.toString();
	}

	public String getScmType(Scm scm) {
		String scmType1 = getScmType(scm.getDeveloperConnection());
		String scmType2 = getScmType(scm.getConnection());
		if (!StringUtils.isEmpty(scmType1)) {
			return scmType1;
		} else if (!StringUtils.isEmpty(scmType2)) {
			return scmType2;
		} else {
			return null;
		}
	}

	public String getScmType(String url) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		String[] tokens = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ":");
		if (tokens == null || tokens.length < 2) {
			return null;
		} else {
			return tokens[1];
		}
	}

	public String getMajorVersion(String version) {
		if (StringUtils.isEmpty(version)) {
			return null;
		}
		int pos = getPos(version);
		if (pos == -1) {
			return version;
		} else {
			return version.substring(0, pos);
		}
	}

	protected int getPos(String version) {
		int pos1 = version.indexOf(".");
		int pos2 = version.indexOf("-");
		// No dot or dash
		if (pos1 == pos2 && pos2 == -1) {
			return -1;
		}
		// Dash but no dot
		if (pos1 == -1 && pos2 != -1) {
			return pos1;
		}
		// Dot but no dash
		if (pos1 != -1 && pos2 == -1) {
			return pos2;
		}

		// Both a dot and a dash, use the first one
		int pos = Math.min(pos1, pos2);

		// Attempt to peek ahead one character after the dot/dash
		int index = pos + 1;

		// If we go past the end of the string, forget it
		if (index >= version.length()) {
			return pos;
		}

		// Is that character an integer?
		char c = version.charAt(index);

		if (isInteger(c)) {
			// If so, include it
			return index + 1;
		} else {
			// If not return the original
			return pos;
		}

	}

	protected boolean isInteger(char c) {
		switch (c) {
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			return true;
		default:
			return false;
		}
	}
}
