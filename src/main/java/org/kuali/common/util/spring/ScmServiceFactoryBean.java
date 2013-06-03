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

import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.service.ScmService;
import org.kuali.common.util.service.ScmType;
import org.kuali.common.util.service.SvnService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class ScmServiceFactoryBean implements FactoryBean<ScmService> {

	String url;
	boolean singleton = true;

	@Override
	public ScmService getObject() {
		Assert.hasText(url, "URL has no text");
		// scm:svn:https://svn.kuali.org/repos/student/trunk
		String[] tokens = StringUtils.split(url, ":");
		String scmType = tokens[1].toUpperCase();
		ScmType type = ScmType.valueOf(scmType);
		switch (type) {
		case SVN:
			return new SvnService();
		case GIT:
			throw new IllegalArgumentException("GIT support is coming soon!");
		default:
			throw new IllegalArgumentException("SCM type [" + scmType + "] is unknown");
		}
	}

	@Override
	public Class<ScmService> getObjectType() {
		return ScmService.class;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

}
