package org.kuali.common.util.spring.env;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * Automatically checks both system properties and environment variables for values
 */
public class StandardEnvironment extends AbstractEnvironment {

	public StandardEnvironment() {
		Properties global = PropertyUtils.getGlobalProperties();
		PropertySource<?> source = new StandardPropertySource(global);
		MutablePropertySources sources = getPropertySources();
		sources.addFirst(source);
	}

}
