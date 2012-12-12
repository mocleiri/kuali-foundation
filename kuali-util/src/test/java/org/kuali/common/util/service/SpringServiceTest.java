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

import java.util.Arrays;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.spring.DefaultSpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SpringServiceTest.class);

	@Test
	public void test() {
		try {
			Properties properties = new Properties();
			properties.setProperty("spring.message", "howdy");

			DefaultSpringContext context = new DefaultSpringContext();
			context.setPropertySources(Arrays.asList(properties));

			SpringService service = new DefaultSpringService();
			service.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
