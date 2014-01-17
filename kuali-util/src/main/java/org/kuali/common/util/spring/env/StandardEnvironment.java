package org.kuali.common.util.spring.env;

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * Automatically check both system properties and environment variables for values
 */
public class StandardEnvironment extends AbstractEnvironment {

	/**
	 * Automatically check both system properties and environment variables for values
	 */
	public StandardEnvironment() {
		Properties global = PropertyUtils.getGlobalProperties();
		PropertySource<?> source = new SysEnvPropertySource(global);
		MutablePropertySources sources = getPropertySources();
		sources.addFirst(source);
	}

}
