package org.kuali.common.util.property;

import java.io.File;

public interface PropertyStorageContext extends PropertyHandlingContext {

	File getFile();

	boolean isSort();

	String getComment();

}
