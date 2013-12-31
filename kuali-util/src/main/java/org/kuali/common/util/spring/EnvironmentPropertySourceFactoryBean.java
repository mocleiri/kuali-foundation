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

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import com.google.common.base.Preconditions;

public class EnvironmentPropertySourceFactoryBean implements FactoryBean<PropertySource<?>> {

	ConfigurableEnvironment env;

	@Override
	public PropertySource<?> getObject() {
		Preconditions.checkNotNull(env, "'env' cannot be null");
		Properties source = PropertySourceUtils.getAllEnumerableProperties(env);
		return new PropertiesPropertySource("environmentProperties", source);
	}

	@Override
	public Class<PropertySource<?>> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public ConfigurableEnvironment getEnv() {
		return env;
	}

	public void setEnv(ConfigurableEnvironment env) {
		this.env = env;
	}
}
