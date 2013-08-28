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

import org.springframework.beans.factory.FactoryBean;

/**
 * Use <code>ScmConfig</code> instead.
 * 
 * @deprecated
 */
@Deprecated
public class ScmServiceFactoryBean implements FactoryBean<org.kuali.common.util.service.ScmService> {

	String url;
	boolean singleton = true;

	@Override
	public org.kuali.common.util.service.ScmService getObject() {
		return org.kuali.common.util.ScmUtils.getScmService(url);
	}

	@Override
	public Class<org.kuali.common.util.service.ScmService> getObjectType() {
		return org.kuali.common.util.service.ScmService.class;
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
