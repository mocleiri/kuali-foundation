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

import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 */
public class ProjectPropertiesLoaderFactoryBean implements FactoryBean<Properties>, InitializingBean, ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(ProjectPropertiesLoaderFactoryBean.class);

	ApplicationContext applicationContext;
	boolean singleton = true;

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(FormatUtils.getDate(System.currentTimeMillis()));
	}

	@Override
	public Properties getObject() throws Exception {
		logger.info(FormatUtils.getDate(System.currentTimeMillis()));
		return null;
	}

	@Override
	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return singleton;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		logger.info(FormatUtils.getDate(System.currentTimeMillis()));
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

}
