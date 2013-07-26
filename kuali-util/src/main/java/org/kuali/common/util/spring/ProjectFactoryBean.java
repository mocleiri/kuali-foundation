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

import org.kuali.common.util.Assert;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.beans.factory.FactoryBean;

@Deprecated
public class ProjectFactoryBean<T> implements FactoryBean<Project> {

	String gav;
	boolean singleton = true;

	@Override
	public Project getObject() {
		Assert.hasText(gav);
		return ProjectUtils.loadProject(gav);
	}

	@Override
	public Class<Project> getObjectType() {
		return Project.class;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	public String getGav() {
		return gav;
	}

	public void setGav(String gav) {
		this.gav = gav;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

}
