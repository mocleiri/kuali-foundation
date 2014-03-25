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
package org.kuali.common.util.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.maven.model.Dependency;
import org.kuali.common.util.nullify.NullUtils;

public class RepositoryUtils {

	private static final String FS = File.separator;
	private static final String GAV_DELIMITER = ":";
	private static final String DEFAULT_MAVEN_REPO_PATH = ".m2" + FS + "repository";

	public static File getDefaultLocalRepository() {
		return new File(FileUtils.getUserDirectoryPath() + FS + DEFAULT_MAVEN_REPO_PATH);
	}

	/**
	 * Copy an artifact from <code>repository</code> into a local repository.
	 */
	public static final void copyArtifactToDirectory(String repository, Artifact artifact, File localRepository) {
		String filename = getFilename(artifact);
		File file = new File(localRepository, filename);
		copyArtifactToFile(repository, artifact, file);
	}

	/**
	 * Copy an artifact from <code>repository</code> to a specific file on the local file system.
	 */
	public static final void copyArtifactToFile(String repository, Artifact artifact, File file) {
		String location = repository + getRepositoryPath(artifact);
		LocationUtils.copyLocationToFile(location, file);
	}

	/**
	 * <p>
	 * Order is <code>groupId:artifactId:version:classifier:type</code>. The ordering here matches the order Maven uses to create actual files. Which is different from what the
	 * toString() method on Maven's Artifact object produces.
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
		List<String> tokens = new ArrayList<String>();
		tokens.add(toEmpty(artifact.getGroupId()));
		tokens.add(toEmpty(artifact.getArtifactId()));
		tokens.add(toEmpty(artifact.getVersion()));
		tokens.add(toEmpty(artifact.getClassifier().orNull()));
		tokens.add(toEmpty(artifact.getType()));
		int delimiterCount = getDelimiterCount(tokens);
		return getDelimitedString(tokens, delimiterCount, GAV_DELIMITER);
	}

	/**
	 * <p>
	 * Order is <code>groupId:artifactId:version:classifier:type:scope</code>. The ordering here matches the order Maven uses to create actual files. As opposed to what the
	 * toString() method on Maven's Dependency object produces.
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
	 *   :kuali-jdbc:::jar                                    - artifactId + type 
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
		tokens.add(toEmpty(dependency.getClassifier().orNull()));
		tokens.add(toEmpty(dependency.getType()));
		tokens.add(toEmpty(dependency.getScope()));
		int delimiterCount = getDelimiterCount(tokens);
		return getDelimitedString(tokens, delimiterCount, GAV_DELIMITER);
	}

	/**
	 * <p>
	 * Order is <code>groupId:artifactId:version:classifier:type:scope</code>.
	 * </p>
	 */
	public static final Artifact parseArtifact(String gav) {
		Assert.noBlanks(gav);

		String[] tokens = StringUtils.splitPreserveAllTokens(gav, GAV_DELIMITER);
		int len = tokens.length;
		Assert.isTrue(len >= 2, "groupId, artifactId, and version are required");
		for (int i = 0; i < len; i++) {
			tokens[i] = NullUtils.trimToNull(tokens[i]);
		}

		String groupId = tokens[0];
		String artifactId = tokens[1];
		String version = tokens[2];
		String classifier = (len > 3) ? tokens[3] : NullUtils.NONE;
		String type = (len > 4) ? tokens[4] : Artifact.Builder.DEFAULT_TYPE;

		return new Artifact.Builder(groupId, artifactId, version).classifier(classifier).type(type).build();
	}

	/**
	 * <p>
	 * Order is <code>groupId:artifactId:version:classifier:type:scope</code>.
	 * </p>
	 */
	public static final Dependency parseDependency(String gav) {
		Assert.noBlanks(gav);

		String[] tokens = StringUtils.splitPreserveAllTokens(gav, GAV_DELIMITER);
		int len = tokens.length;
		Assert.isTrue(len >= 2, "groupId, artifactId, and version are required");
		for (int i = 0; i < len; i++) {
			tokens[i] = NullUtils.trimToNull(tokens[i]);
		}

		String groupId = tokens[0];
		String artifactId = tokens[1];
		String version = tokens[2];
		String classifier = (len > 3) ? tokens[3] : NullUtils.NONE;
		String type = (len > 4) ? tokens[4] : Dependency.Builder.DEFAULT_TYPE;
		String scope = (len > 5) ? tokens[5] : Dependency.Builder.DEFAULT_SCOPE;

		return new Dependency.Builder(groupId, artifactId, version).classifier(classifier).type(type).scope(scope).build();
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
			String token = toEmpty(tokens.get(i));
			if (!StringUtils.isEmpty(token)) {
				count = i + 1;
			}
		}
		return count == 0 ? tokens.size() : count;
	}

	/**
	 * Return null if token is blank, "NULL", or "NONE"
	 * 
	 * @deprecated Use NullUtils.isNullOrNone() instead
	 */
	@Deprecated
	public static String toNull(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		if (NullUtils.isNullOrNone(token)) {
			return null;
		}
		return token;
	}

	/**
	 * Return the empty string if token is blank, "NULL", or "NONE"
	 */
	public static String toEmpty(String token) {
		if (StringUtils.isBlank(token)) {
			return "";
		}
		if (NullUtils.isNullOrNone(token)) {
			return "";
		}
		return token;
	}

	/**
	 * <pre>
	 *  org.kuali.common:kuali-util:2.0.1 -> org/kuali/common/kuali-util/2.0.1
	 * </pre>
	 */
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
	 * <pre>
	 *  org.kuali.common:kuali-util:2.0.1::jar    -> kuali-util-2.0.1.jar
	 *  org.kuali.common:kuali-util:2.0.1:sql:jar -> kuali-util-2.0.1-sql.jar
	 * </pre>
	 */
	public static final String getFilename(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(artifact.getArtifactId());
		sb.append("-");
		sb.append(artifact.getVersion());
		if (artifact.getClassifier().isPresent()) {
			sb.append("-");
			sb.append(artifact.getClassifier().get());
		}
		sb.append(".");
		sb.append(artifact.getType());
		return sb.toString();
	}

	public static final File getFile(File localRepositoryDir, Artifact artifact) {
		String path = getRepositoryPath(artifact);
		String filename = getFilename(artifact);
		return new File(localRepositoryDir.getAbsolutePath() + FS + path, filename);
	}

	public static final boolean exists(File localRepositoryDir, Artifact artifact) {
		File file = getFile(localRepositoryDir, artifact);
		return LocationUtils.exists(file);
	}

}
