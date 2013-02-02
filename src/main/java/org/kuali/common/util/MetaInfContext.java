package org.kuali.common.util;

import java.io.File;
import java.util.List;

public interface MetaInfContext {

	File getBaseDir();

	File getOutputFile();

	String getPrefix();

	boolean isSort();

	List<String> getIncludes();

	List<String> getExcludes();

}
