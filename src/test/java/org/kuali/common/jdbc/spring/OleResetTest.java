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

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;

public class OleResetTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			List<PropertySource<?>> sources = getPropertySources(ss, OleMavenPropertySourceConfig.class);
			PropertySourceContext psc = new PropertySourceContext();
			psc.setRemoveExistingSources(true);
			psc.setSources(sources);
			SpringContext context = new SpringContext();
			context.setPropertySourceContext(psc);
			context.setAnnotatedClasses(CollectionUtils.asList(ResetConfig.class, ResetController.class));
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected List<PropertySource<?>> getPropertySources(SpringService service, Class<?> annotatedClass) {
		return getPropertySources(service, annotatedClass, "mavenProperties", OlePropertiesConfig.OLE_MAVEN_PROPS);
	}

	protected List<PropertySource<?>> getPropertySources(SpringService service, Class<?> annotatedClass, String mavenPropertiesBeanName, Properties mavenProperties) {
		ConfigurableApplicationContext parent = SpringUtils.getContextWithPreRegisteredBean(mavenPropertiesBeanName, mavenProperties);
		AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
		child.setParent(parent);
		child.register(annotatedClass);
		child.refresh();
		return SpringUtils.getPropertySources(child);
	}

}
