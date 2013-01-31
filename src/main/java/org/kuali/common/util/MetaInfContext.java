package org.kuali.common.util;

import java.io.File;

public interface MetaInfContext {

	File getBaseDir();

	File getOutputFile();

	String getPrefix();

	boolean isSort();

}
