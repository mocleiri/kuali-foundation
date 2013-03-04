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

import org.kuali.common.util.Artifact;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.RepositoryUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class ArtifactPathFactoryBean extends Artifact implements FactoryBean<String> {

	File localRepositoryDir = RepositoryUtils.getDefaultLocalRepositoryDir();
	boolean mustExist;

	@Override
	public String getObject() throws Exception {

		Assert.notNull(localRepositoryDir);
		Assert.notNull(getGroupId());
		Assert.notNull(getArtifactId());
		Assert.notNull(getVersion());
		Assert.notNull(getPackaging());

		File file = RepositoryUtils.getFile(localRepositoryDir, this);
		validate(file);
		return LocationUtils.getCanonicalPath(file);
	}

	protected void validate(File file) {
		if (!file.exists() && mustExist) {
			throw new IllegalStateException(file + " does not exist");
		}
	}

	@Override
	public Class<String> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public File getLocalRepositoryDir() {
		return localRepositoryDir;
	}

	public void setLocalRepositoryDir(File localRepositoryDir) {
		this.localRepositoryDir = localRepositoryDir;
	}

	public boolean isMustExist() {
		return mustExist;
	}

	public void setMustExist(boolean mustExist) {
		this.mustExist = mustExist;
	}

}
