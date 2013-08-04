package org.kuali.common.util.metainf.model;

public class PropertiesContext {

	public static final PropertiesContext DEFAULT_PROPERTIES_CONTEXT = new PropertiesContext();

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

	private final boolean includePropertiesFile;
	private final boolean includeFileSizes;
	private final boolean includeLineCounts;

	public boolean isIncludePropertiesFile() {
		return includePropertiesFile;
	}

	public boolean isIncludeFileSizes() {
		return includeFileSizes;
	}

	public boolean isIncludeLineCounts() {
		return includeLineCounts;
	}

}
