package org.kuali.common.util.properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.property.PropertyFormat;

public interface Location {

	Mode getMissingMode();

	String getEncoding();

	PropertyFormat getFormat();

	String getValue();

}
