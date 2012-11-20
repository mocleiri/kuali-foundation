package org.kuali.common.util.property;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.property.modifier.PropertyModifier;

public interface PropertyContext {

	String getEncoding();

	List<PropertyModifier> getModifiers();

	void beforeModify(Properties properties);

}