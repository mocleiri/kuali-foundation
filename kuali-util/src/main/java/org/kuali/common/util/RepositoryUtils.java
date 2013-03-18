/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.nullify.NullUtils;

public class RepositoryUtils {

	private static final String FS = File.separator;
	private static final String DEFAULT_MAVEN_REPO_PATH = ".m2" + FS + "repository";

	public static final void copyArtifact(String repository, Artifact artifact) {
		File file = getFile(artifact);
		copyArtifactToFile(repository, artifact, file);
	}

	public static final void copyArtifactToDirectory(String repository, Artifact artifact, File directory) {
		String filename = getFilename(artifact);
		File file = new File(directory, filename);
		copyArtifactToFile(repository, artifact, file);
	}

	public static final void copyArtifactToFile(String repository, Artifact artifact, File file) {
		String location = repository + getRepositoryPath(artifact);
		LocationUtils.copyLocationToFile(location, file);
	}

	/**
	 * Order is <code>groupId:artifactId:version:classifier:type</code>. There are always 4 colon's in returned string. Empty fields are simply omitted.
	 * 
	 * <pre>
	 *   ::::                                          - Every field is blank
	 *   org.kuali.common::::                          - groupId only
	 *   org.kuali.common:kuali-jdbc:::                - groupId + artifactId
	 *   org.kuali.common:kuali-jdbc:1.0.0::           - groupId + artifactId + version 
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp:     - groupId + artifactId + version + classifier
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp:jar  - groupId + artifactId + version + classifier + type
	 *   org.kuali.common:kuali-jdbc:1.0.0::jar        - no classifier
	 *   org.kuali.common:kuali-jdbc:1.0.0::           - no classifier or type
	 *   org.kuali.common:kuali-jdbc::webapp:jar       - no version
	 * </pre>
	 */
	public static final String toString(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(toGAVToken(artifact.getGroupId()));
		sb.append(":");
		sb.append(toGAVToken(artifact.getArtifactId()));
		sb.append(":");
		sb.append(toGAVToken(artifact.getVersion()));
		sb.append(":");
		sb.append(toGAVToken(artifact.getClassifier()));
		sb.append(":");
		sb.append(toGAVToken(artifact.getType()));
		return sb.toString();
	}

	protected static String toGAVToken(String token) {
		if (StringUtils.isBlank(token)) {
			return "";
		}
		if (NullUtils.isNullOrNone(token)) {
			return "";
		}
		return token;
	}

	public static final String getRepositoryPath(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(Str.getPath(artifact.getGroupId()));
		sb.append(FS);
		sb.append(artifact.getArtifactId());
		sb.append(FS);
		sb.append(artifact.getVersion());
		return sb.toString();
	}

	/**
	 * Return true if classifier should not become part of the filename
	 */
	protected static boolean isSkipClassifier(String classifier) {
		return StringUtils.isBlank(classifier) || NullUtils.isNullOrNone(classifier);
	}

	public static final String getFilename(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(artifact.getArtifactId());
		sb.append("-");
		sb.append(artifact.getVersion());
		if (!isSkipClassifier(artifact.getClassifier())) {
			sb.append("-");
			sb.append(artifact.getClassifier());
		}
		sb.append(".");
		sb.append(artifact.getType());
		return sb.toString();
	}

	public static final File getDefaultLocalRepositoryDir() {
		return new File(FileUtils.getUserDirectoryPath() + FS + DEFAULT_MAVEN_REPO_PATH);
	}

	public static final File getFile(Artifact artifact) {
		return getFile(getDefaultLocalRepositoryDir(), artifact);
	}

	public static final File getFile(File localRepositoryDir, Artifact artifact) {
		String path = getRepositoryPath(artifact);
		String filename = getFilename(artifact);
		return new File(localRepositoryDir.getAbsolutePath() + FS + path, filename);
	}

}
