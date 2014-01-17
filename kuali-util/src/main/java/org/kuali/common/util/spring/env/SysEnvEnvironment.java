package org.kuali.common.util.spring.env;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * Automatically check both system properties and environment variables for values
 */
public class SysEnvEnvironment extends AbstractEnvironment {

	public SysEnvEnvironment() {
		PropertySource<?> source = new SysEnvPropertySource();
		MutablePropertySources sources = getPropertySources();
		sources.addFirst(source);
	}

}
