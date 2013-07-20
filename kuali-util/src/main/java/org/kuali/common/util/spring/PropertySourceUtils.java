package org.kuali.common.util.spring;

import org.kuali.common.util.spring.service.PropertySourceContext;
import org.kuali.common.util.spring.service.SpringContext;
import org.springframework.core.env.PropertySource;

public class PropertySourceUtils {

	public static SpringContext getSinglePropertySourceContext(PropertySource<?> source) {
		// Setup a property source context such that this property source is the only one registered with Spring
		// This PropertySource will be the ONLY thing used to resolve placeholders
		PropertySourceContext psc = new PropertySourceContext(source, true);

		// Setup a Spring context
		SpringContext context = new SpringContext();

		// Supply Spring with our PropertySource
		context.setPropertySourceContext(psc);

		// Return a Spring context configured with a single property source
		return context;
	}

}
