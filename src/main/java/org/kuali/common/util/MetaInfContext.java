package org.kuali.common.util;

import java.io.File;
import java.util.List;

public interface MetaInfContext {

	public static final boolean DEFAULT_SORT_VALUE = true;
	public static final String DEFAULT_PREFIX_VALUE = "classpath:";

	File getBaseDir();

	File getOutputFile();

	String getPrefix();

	boolean isSort();

	List<String> getIncludes();

	List<String> getExcludes();

}
