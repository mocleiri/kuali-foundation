package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.modifier.PropertyProcessor;

public interface PropertyContext {

	String getEncoding();

	List<PropertyProcessor> getModifiers();

	void initialize(Properties properties);

}