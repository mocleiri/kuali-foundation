package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.processor.PropertyProcessor;
import org.springframework.util.PropertyPlaceholderHelper;

public class DefaultStringResolver implements StringResolver {

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	List<PropertyProcessor> processors;

	public DefaultStringResolver() {
		this(Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER, null);
	}

	public DefaultStringResolver(PropertyPlaceholderHelper helper, List<PropertyProcessor> processors) {
		super();
		this.helper = helper;
		this.processors = processors;
	}

	@Override
	public String resolve(String location, Properties properties) {
		Properties duplicate = PropertyUtils.duplicate(properties);
		for (PropertyProcessor processor : CollectionUtils.toEmpty(processors)) {
			processor.process(duplicate);
		}
		return helper.replacePlaceholders(location, duplicate);
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public List<PropertyProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<PropertyProcessor> processors) {
		this.processors = processors;
	}

}
