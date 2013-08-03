package org.kuali.common.util.metainf.model;

public final class PropertiesContext {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (includeFileSizes ? 1231 : 1237);
		result = prime * result + (includeLineCounts ? 1231 : 1237);
		result = prime * result + (includePropertiesFile ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertiesContext other = (PropertiesContext) obj;
		if (includeFileSizes != other.includeFileSizes)
			return false;
		if (includeLineCounts != other.includeLineCounts)
			return false;
		if (includePropertiesFile != other.includePropertiesFile)
			return false;
		return true;
	}

}
