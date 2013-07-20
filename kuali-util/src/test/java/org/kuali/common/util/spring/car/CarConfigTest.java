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
package org.kuali.common.util.spring.car;

import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.PropertySource;

@Deprecated
public class CarConfigTest {

	@Test
	public void test() {

		try {
			System.setProperty("car.make", "ford");

			SpringService ss = new DefaultSpringService();

			List<PropertySource<?>> propertySources = SpringUtils.getPropertySources(CarPropertySourcesConfig.class);

			SpringContext sc = new SpringContext();
			sc.setAnnotatedClasses(CollectionUtils.asList(CarConfig.class));
			sc.setPropertySourceContext(new PropertySourceContext(propertySources, true));
			ss.load(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
