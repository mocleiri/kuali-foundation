package org.kuali.common.util.spring;

import java.util.Properties;

import org.springframework.core.env.PropertySource;

/**
 * A <code>PropertySource</code> backed up by a <code>Properties</code> object.
 */
public class PropertiesPropertySource extends PropertySource<Properties> {

	public PropertiesPropertySource(String name, Properties source) {
		super(name, source);
	}

	public PropertiesPropertySource(String name) {
		super(name);
	}

	@Override
	public Object getProperty(String name) {
		return source.getProperty(name);
	}

}
