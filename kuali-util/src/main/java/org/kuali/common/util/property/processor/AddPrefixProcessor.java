package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;

public class AddPrefixProcessor implements PropertyProcessor {

	String prefix;

	public AddPrefixProcessor() {
		this(null);
	}

	public AddPrefixProcessor(String prefix) {
		super();
		this.prefix = prefix;
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		Properties duplicate = PropertyUtils.duplicate(properties);
		properties.clear();
		for (String key : keys) {
			String value = duplicate.getProperty(key);
			String newKey = prefix + "." + key;
			properties.setProperty(newKey, value);
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
