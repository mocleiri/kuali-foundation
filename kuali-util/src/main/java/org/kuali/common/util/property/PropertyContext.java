package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.property.modifier.PropertyModifier;

public interface PropertyContext {

	String getEncoding();

	List<PropertyModifier> getModifiers();


}