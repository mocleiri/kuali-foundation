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
import java.util.List;

import org.kuali.common.util.maven.model.Artifact;

public interface LocalRepositoryService {

	void copyArtifact(Artifact artifact, String repository);

	void copyArtifacts(List<Artifact> artifacts, String repository);

	File getFile(Artifact artifact);

	List<File> getFiles(List<Artifact> artifacts);

	File getLocalRepositoryDir();

}
