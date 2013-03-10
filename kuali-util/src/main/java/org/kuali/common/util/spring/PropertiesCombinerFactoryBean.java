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
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class PropertiesCombinerFactoryBean implements FactoryBean<Properties> {

	List<Properties> listOfProperties;

	@Override
	public Properties getObject() throws Exception {
		Assert.notNull(listOfProperties, "listOfProperties is null");
		return PropertyUtils.combine(listOfProperties);
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public List<Properties> getListOfProperties() {
		return listOfProperties;
	}

	public void setListOfProperties(List<Properties> listOfProperties) {
		this.listOfProperties = listOfProperties;
	}

}
