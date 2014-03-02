package org.kuali.common.util.spring.env;

import org.kohsuke.MetaInfServices;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

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
@MetaInfServices(Environment.class)
public class SysEnvEnvironment extends AbstractEnvironment {

	public SysEnvEnvironment() {
		PropertySource<?> source = new SysEnvPropertySource();
		MutablePropertySources sources = super.getPropertySources();
		sources.addFirst(source);
	}

}
