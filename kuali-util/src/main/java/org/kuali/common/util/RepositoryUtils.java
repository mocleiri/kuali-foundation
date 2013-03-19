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
import java.util.ArrayList;
import java.util.List;

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
	 * <p>
	 * Order is <code>groupId:artifactId:version:classifier:type</code>.
	 * </p>
	 * 
	 * <p>
	 * Trailing <code>:</code>'s are omitted.
	 * </p>
	 * 
	 * <p>
	 * If every field is left blank, <code>::::</code> is returned.
	 * </p>
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp:jar  - groupId + artifactId + version + classifier + type
	 *   org.kuali.common:kuali-jdbc:1.0.0::jar        - no classifier
	 *   ::::                                          - Every field is blank
	 *   org.kuali.common                              - groupId only
	 *   ::::jar                                       - type only
	 *   :kuali-jdbc:::jar                             - no groupId, version, classifier, or type 
	 *   org.kuali.common:kuali-jdbc                   - groupId + artifactId
	 *   org.kuali.common:kuali-jdbc:1.0.0             - groupId + artifactId + version 
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp      - no type
	 *   org.kuali.common:kuali-jdbc:1.0.0             - no classifier or type
	 *   org.kuali.common:kuali-jdbc::webapp:jar       - no version
	 * </pre>
	 */
	public static final String toString(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(toEmpty(artifact.getGroupId()));
		sb.append(":");
		sb.append(toEmpty(artifact.getArtifactId()));
		sb.append(":");
		sb.append(toEmpty(artifact.getVersion()));
		sb.append(":");
		sb.append(toEmpty(artifact.getClassifier()));
		sb.append(":");
		sb.append(toEmpty(artifact.getType()));
		return sb.toString();
	}

	/**
	 * <p>
	 * Order is <code>groupId:artifactId:version:classifier:type:scope</code>.
	 * </p>
	 * 
	 * <p>
	 * Trailing <code>:</code>'s are omitted.
	 * </p>
	 * 
	 * <p>
	 * If every field is left blank, <code>:::::</code> is returned.
	 * </p>
	 * 
	 * <pre>
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp:jar:compile - groupId + artifactId + version + classifier + type + scope
	 *   org.kuali.common:kuali-jdbc:1.0.0::jar:compile       - no classifier
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp:jar:        - no scope
	 *   :::::                                                - Every field is blank
	 *   org.kuali.common                                     - groupId only
	 *   :::::compile                                         - scope only
	 *   :kuali-jdbc::jar                                     - artifactId + type 
	 *   org.kuali.common:kuali-jdbc                          - groupId + artifactId
	 *   org.kuali.common:kuali-jdbc:1.0.0                    - groupId + artifactId + version 
	 *   org.kuali.common:kuali-jdbc:1.0.0:webapp             - groupId + artifactId + version + classifier
	 *   org.kuali.common:kuali-jdbc:1.0.0:::compile          - no classifier or type
	 *   org.kuali.common:kuali-jdbc::webapp:jar:compile      - no version
	 * </pre>
	 */
	public static final String toString(Dependency dependency) {
		List<String> tokens = new ArrayList<String>();
		tokens.add(toEmpty(dependency.getGroupId()));
		tokens.add(toEmpty(dependency.getArtifactId()));
		tokens.add(toEmpty(dependency.getVersion()));
		tokens.add(toEmpty(dependency.getClassifier()));
		tokens.add(toEmpty(dependency.getType()));
		tokens.add(toEmpty(dependency.getScope()));
		int delimiterCount = getDelimiterCount(tokens);
		return getDelimitedString(tokens, delimiterCount, ":");
	}

	protected static final String getDelimitedString(List<String> tokens, int delimiterCount, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.size(); i++) {
			if (i != 0 && i < delimiterCount) {
				sb.append(delimiter);
			}
			sb.append(tokens.get(i));
		}
		return sb.toString();
	}

	protected static final int getDelimiterCount(List<String> tokens) {
		int count = 0;
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			if (!StringUtils.isEmpty(token)) {
				count = i + 1;
			}
		}
		return count;
	}

	/**
	 * Return the empty string if token is blank, "NULL", or "NONE"
	 */
	protected static String toEmpty(String token) {
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
	 * Return true if classifier should become part of the filename
	 */
	protected static boolean addClassifierToFilename(String classifier) {
		return !StringUtils.isBlank(classifier) && !NullUtils.isNullOrNone(classifier);
	}

	public static final String getFilename(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(artifact.getArtifactId());
		sb.append("-");
		sb.append(artifact.getVersion());
		if (addClassifierToFilename(artifact.getClassifier())) {
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
