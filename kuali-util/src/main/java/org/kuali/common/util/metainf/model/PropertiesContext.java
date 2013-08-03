package org.kuali.common.util.metainf.model;

public class PropertiesContext {

	public PropertiesContext() {
		this(DEFAULT_INCLUDE_PROPERTIES_FILE, DEFAULT_INCLUDE_FILE_SIZES, DEFAULT_INCLUDE_LINE_COUNTS);
	}

	public PropertiesContext(boolean includePropertiesFile, boolean includeFileSizes, boolean includeLineCounts) {
		super();
		this.includePropertiesFile = includePropertiesFile;
		this.includeFileSizes = includeFileSizes;
		this.includeLineCounts = includeLineCounts;
	}

	public static final boolean DEFAULT_INCLUDE_PROPERTIES_FILE = false;
	public static final boolean DEFAULT_INCLUDE_FILE_SIZES = true;
	public static final boolean DEFAULT_INCLUDE_LINE_COUNTS = false;

	boolean includePropertiesFile = DEFAULT_INCLUDE_PROPERTIES_FILE;
	boolean includeFileSizes = DEFAULT_INCLUDE_FILE_SIZES;
	boolean includeLineCounts = DEFAULT_INCLUDE_LINE_COUNTS;

	public boolean isIncludePropertiesFile() {
		return includePropertiesFile;
	}

	public void setIncludePropertiesFile(boolean includePropertiesFile) {
		this.includePropertiesFile = includePropertiesFile;
	}

	public boolean isIncludeFileSizes() {
		return includeFileSizes;
	}

	public void setIncludeFileSizes(boolean includeFileSizes) {
		this.includeFileSizes = includeFileSizes;
	}

	public boolean isIncludeLineCounts() {
		return includeLineCounts;
	}

	public void setIncludeLineCounts(boolean includeLineCounts) {
		this.includeLineCounts = includeLineCounts;
	}

}
