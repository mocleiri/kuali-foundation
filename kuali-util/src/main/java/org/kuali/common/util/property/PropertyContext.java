package org.kuali.common.util.property;

import java.util.List;

import org.springframework.util.PropertyPlaceholderHelper;

public interface PropertyContext {

	String getEncoding();

	List<PropertyModifier> getModifiers();

	PropertyPlaceholderHelper getHelper();

}