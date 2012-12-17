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

import java.util.List;

public class DefaultInjectionContext implements InjectionContext {

	List<String> locations;
	List<String> beanNames;
	List<Object> beans;
	boolean injectBeans;

	@Override
    public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	@Override
    public List<String> getBeanNames() {
		return beanNames;
	}

	public void setBeanNames(List<String> beanNames) {
		this.beanNames = beanNames;
	}

	@Override
    public List<Object> getBeans() {
		return beans;
	}

	public void setBeans(List<Object> beans) {
		this.beans = beans;
	}

	@Override
    public boolean isInjectBeans() {
		return injectBeans;
	}

	public void setInjectBeans(boolean injectBeans) {
		this.injectBeans = injectBeans;
	}
}
