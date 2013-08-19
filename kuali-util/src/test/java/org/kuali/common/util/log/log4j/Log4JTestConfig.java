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
package org.kuali.common.util.log.log4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log.log4j.model.Log4JContext;
import org.kuali.common.util.log.log4j.spring.Log4JConfig;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class, Log4JConfig.class })
public class Log4JTestConfig {

	@Autowired
	Log4JService service;

	@Autowired
	Log4JConfig config;

	@Autowired
	Project project;

	@Test
	public void test() {
		try {
			Log4JContext context = config.log4JContextMaven();
			String xml = service.toXml(context);
			System.out.println(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
