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
package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import org.junit.Test;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;

public class OleResetTest {

	@Test
	public void test() {
		try {
			// Default Spring service will do what we need
			SpringService ss = new DefaultSpringService();

			// The main project who's properties need to be loaded and made available to Spring
			ProjectContext project = new OleTestProjectContext();

			// Other projects who's properties we need to make available to Spring
			ProjectContext other = new JdbcProjectContext();

			// The annotated java class containing the Spring configuration that does what we need it to do
			Class<?> annotatedClass = SqlControllerConfig.class;

			// Prepare a Spring context
			SpringContext context = SpringUtils.getSpringContext(annotatedClass, project, Arrays.asList(other));

			// Execute Spring
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
