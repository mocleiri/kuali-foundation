package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.processor.PropertyProcessor;

public interface PropertyContext {

	String getEncoding();

	List<PropertyProcessor> getProcessors();

	void initialize(Properties properties);

}