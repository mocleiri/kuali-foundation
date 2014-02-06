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
package org.kuali.common.util.spring;

import java.io.File;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.springframework.beans.factory.FactoryBean;

/**
 * @deprecated
 */
@Deprecated
public class ArtifactPathFactoryBean extends org.kuali.common.util.Artifact implements FactoryBean<String> {

	File localRepositoryDir = org.kuali.common.util.RepositoryUtils.getDefaultLocalRepositoryDir();
	boolean mustExist;

	@Override
	public String getObject() {

		Assert.notNull(localRepositoryDir);
		Assert.hasText(getGroupId());
		Assert.hasText(getArtifactId());
		Assert.hasText(getVersion());
		Assert.hasText(getType());

		File file = org.kuali.common.util.RepositoryUtils.getFile(localRepositoryDir, this);
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
