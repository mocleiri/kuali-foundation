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
package org.kuali.common.util.spring;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.kuali.common.util.Artifact;
import org.springframework.beans.factory.FactoryBean;

public class MavenRepoFileFactoryBean extends Artifact implements FactoryBean<String> {

	private static final String FS = File.separator;

	String repositoryPath = FileUtils.getUserDirectoryPath() + FS + ".m2" + FS + "repository";

	@Override
	public String getObject() throws Exception {
		return null;
	}

	@Override
	public Class<String> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getRepositoryPath() {
		return repositoryPath;
	}

	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}

}
