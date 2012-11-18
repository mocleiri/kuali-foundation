package org.kuali.common.util.property;

import java.util.List;

public interface PropertyContext {

	String getEncoding();

	List<PropertyModifier> getModifiers();


}