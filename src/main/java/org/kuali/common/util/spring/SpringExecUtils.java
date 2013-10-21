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

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.service.PropertySourceContext;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.springframework.core.env.PropertySource;

public class SpringExecUtils {

	/**
	 * Return an executable that resolves all placeholder values against <code>source</code>.
	 */
	public static SpringExecutable getSpringExecutable(SpringService service, PropertySource<?> source, Class<?> annotatedClass, List<String> activeProfiles) {
		SpringContext context = getSinglePropertySourceContext(source);
		context.setActiveProfiles(activeProfiles);
		context.setAnnotatedClasses(CollectionUtils.asList(annotatedClass));
		return new SpringExecutable(service, context);
	}

	/**
	 * Return an executable that resolves all placeholder values against <code>source</code>.
	 */
	public static SpringExecutable getSpringExecutable(SpringService service, PropertySource<?> source, Class<?> annotatedClass) {
		return getSpringExecutable(service, source, annotatedClass, Collections.<String> emptyList());
	}

	/**
	 * Return a SpringExecutable for the PropertySource and annotatedClass passed in
	 */
	@Deprecated
	public static SpringExecutable getSpringExecutable(PropertySource<?> source, Class<?> annotatedClass) {
		return getSpringExecutable(SpringExecutable.DEFAULT_SPRING_SERVICE, source, annotatedClass);
	}

	/**
	 * Return a SpringContext that resolves all placeholders from the PropertySource passed in
	 */
	public static SpringContext getSinglePropertySourceContext(PropertySource<?> source) {
		// Set things up such that this PropertySource is the only one registered with Spring
		// This PropertySource will be the ONLY thing used to resolve placeholders
		PropertySourceContext psc = new PropertySourceContext(source, true);

		// Return a Spring context configured with the PropertySourceContext
		return new SpringContext(psc);
	}

}
