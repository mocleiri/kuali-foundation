package org.kuali.common.util.service;

import org.springframework.core.env.PropertySource;

public class PropertySourceContext {

	PropertySourcePriority priority;
	PropertySource<?> source;

	public PropertySourcePriority getPriority() {
		return priority;
	}

	public void setPriority(PropertySourcePriority priority) {
		this.priority = priority;
	}

	public PropertySource<?> getSource() {
		return source;
	}

	public void setSource(PropertySource<?> source) {
		this.source = source;
	}

}
