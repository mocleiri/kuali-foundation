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

	public static final String toString(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(artifact.getGroupId());
		sb.append(":");
		sb.append(artifact.getArtifactId());
		sb.append(":");
		sb.append(artifact.getVersion());
		if (!isSkipClassifier(artifact)) {
			sb.append(":");
			sb.append(artifact.getClassifier());
		}
		sb.append(":");
		sb.append(artifact.getPackaging());
		return sb.toString();
	}

	protected static boolean isSkipClassifier(Artifact artifact) {
		return StringUtils.isBlank(artifact.getClassifier()) || NullUtils.isNullOrNone(artifact.getClassifier());
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

	public static final String getFilename(Artifact artifact) {
		StringBuilder sb = new StringBuilder();
		sb.append(artifact.getArtifactId());
		sb.append("-");
		sb.append(artifact.getVersion());
		if (!isSkipClassifier(artifact)) {
			sb.append(":");
			sb.append(artifact.getClassifier());
		}
		sb.append(".");
		sb.append(artifact.getPackaging());
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
