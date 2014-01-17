package org.kuali.common.util.spring.env;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.MutablePropertySources;

/**
 * This environment contains both system properties and environment variables and automatically checks both whenever a property is requested.
 * 
 * <pre>
 *   foo.barBaz -> env.FOO_BAR_BAZ
 *                 FOO_BAR_BAZ
 *                 env.foo_bar_baz
 *                 foo_bar_baz
 * </pre>
 */
public class SysEnvEnvironment extends AbstractEnvironment {

	public SysEnvEnvironment() {
		SysEnvPropertySource source = new SysEnvPropertySource();
		MutablePropertySources sources = getPropertySources();
		sources.addFirst(source);
	}

}
