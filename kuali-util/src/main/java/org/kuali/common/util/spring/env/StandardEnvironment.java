package org.kuali.common.util.spring.env;

import org.kuali.common.util.PropertyUtils;
import org.springframework.core.env.AbstractEnvironment;

/**
 * Automatically checks both system properties and environment variables for values
 */
public class StandardEnvironment extends AbstractEnvironment {

	public StandardEnvironment() {
		super.getPropertySources().addFirst(new StandardPropertySource("standard", PropertyUtils.getGlobalProperties()));
	}

}
