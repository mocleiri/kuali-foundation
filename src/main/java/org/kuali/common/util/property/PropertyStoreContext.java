package org.kuali.common.util.property;

import java.io.File;

public interface PropertyStoreContext extends PropertyContext {

	File getFile();

	String getComment();

}
