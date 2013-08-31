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
import org.kuali.common.util.Assert;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.maven.model.Artifact;

import com.google.common.collect.ImmutableList;

public final class DefaultLocalRepositoryService implements LocalRepositoryService {

	private static final String FS = File.separator;
	private static final String DEFAULT_MAVEN_REPO_PATH = ".m2" + FS + "repository";

	private final File localRepository;

	public DefaultLocalRepositoryService() {
		this(getDefaultLocalRepositoryDir());
	}

	public DefaultLocalRepositoryService(File localRepository) {
		Assert.noNulls(localRepository);
		this.localRepository = new CanonicalFile(localRepository);
	}

	@Override
	public void copyArtifact(Artifact artifact, String repository) {
		copyArtifacts(ImmutableList.of(artifact), repository);
	}

	@Override
	public void copyArtifacts(List<Artifact> artifacts, String repository) {
		Assert.noNulls(artifacts);
		Assert.noBlanks(repository);
		for (Artifact artifact : artifacts) {
			File file = getFile(artifact);
			RepositoryUtils.copyArtifactToFile(repository, artifact, file);
		}
	}

	@Override
	public List<File> getFiles(List<Artifact> artifacts) {
		List<File> files = new ArrayList<File>();
		for (Artifact artifact : artifacts) {
			File file = getFile(artifact);
			files.add(file);
		}
		return files;
	}

	@Override
	public File getFile(Artifact artifact) {
		return RepositoryUtils.getFile(localRepository, artifact);
	}

	public static File getDefaultLocalRepositoryDir() {
		return new File(FileUtils.getUserDirectoryPath() + FS + DEFAULT_MAVEN_REPO_PATH);
	}

	@Override
	public File getLocalRepository() {
		return localRepository;
	}

}
