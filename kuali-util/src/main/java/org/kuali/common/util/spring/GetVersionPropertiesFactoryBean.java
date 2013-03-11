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

import java.util.Properties;

import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.kuali.common.util.property.processor.VersionProcessor;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
 */
public class GetVersionPropertiesFactoryBean extends VersionProcessor implements FactoryBean<Properties> {

	String prefix = "project.version";
	String version;

	@Override
	public Properties getObject() throws Exception {
		Version v = VersionUtils.getVersion(version);
		return getVersionProperties(prefix, v);
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
