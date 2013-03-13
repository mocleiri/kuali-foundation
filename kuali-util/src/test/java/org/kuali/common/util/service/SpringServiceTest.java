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
package org.kuali.common.util.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.spring.PropertiesPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.PropertySource;

public class SpringServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringServiceTest.class);

	@Test
	public void test() {
		try {
			logger.debug("");
			Properties properties = new Properties();
			properties.setProperty("spring.message", "foo");
			PropertiesPropertySource pps = new PropertiesPropertySource("properties", properties);
			List<PropertySource<?>> sources = new ArrayList<PropertySource<?>>();
			sources.add(pps);

			SpringContext context = new SpringContext();
			context.setPropertySources(sources);
			context.setLocations(Arrays.asList("classpath:org/kuali/common/util/SimpleExecutable-context.xml"));
			SpringService ss = new DefaultSpringService();
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
