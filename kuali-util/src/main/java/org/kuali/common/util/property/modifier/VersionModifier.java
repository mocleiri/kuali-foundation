package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.kuali.common.util.property.Constants;

public class VersionModifier implements PropertyModifier {

	String majorSuffix = Constants.DEFAULT_MAJOR_VERSION_SUFFIX;
	String minorSuffix = Constants.DEFAULT_MINOR_VERSION_SUFFIX;
	String incrementalSuffix = Constants.DEFAULT_INCREMENTAL_VERSION_SUFFIX;
	String qualifierSuffix = Constants.DEFAULT_QUALIFIER_VERSION_SUFFIX;
	String trimmedSuffix = Constants.DEFAULT_TRIMMED_VERSION_SUFFIX;
	String snapshotSuffix = Constants.DEFAULT_SNAPSHOT_VERSION_SUFFIX;

	List<String> includes;
	List<String> excludes;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public VersionModifier() {
		this(null);
	}

	public VersionModifier(List<String> includes) {
		super();
		this.includes = includes;
	}

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		Properties versionProperties = new Properties();
		for (String key : keys) {
			String version = properties.getProperty(key);
			Version v = VersionUtils.getVersion(version);
			versionProperties.putAll(getVersionProperties(key, v));
		}
		List<String> versionKeys = PropertyUtils.getSortedKeys(versionProperties);
		for (String versionKey : versionKeys) {
			String versionValue = versionProperties.getProperty(versionKey);
			PropertyUtils.setProperty(properties, versionKey, versionValue, propertyOverwriteMode);
		}
	}

	protected Properties getVersionProperties(String key, Version v) {
		Properties properties = new Properties();
		if (v.getMajor() != null) {
			String newKey = key + "." + majorSuffix;
			properties.setProperty(newKey, v.getMajor());
		}
		if (v.getMinor() != null) {
			String newKey = key + "." + minorSuffix;
			properties.setProperty(newKey, v.getMinor());
		}
		if (v.getIncremental() != null) {
			String newKey = key + "." + incrementalSuffix;
			properties.setProperty(newKey, v.getIncremental());
		}
		if (v.getQualifier() != null) {
			String newKey = key + "." + qualifierSuffix;
			properties.setProperty(newKey, v.getQualifier());
		}
		if (v.getTrimmed() != null) {
			String newKey = key + "." + trimmedSuffix;
			properties.setProperty(newKey, v.getTrimmed());
		}
		String newKey = key + "." + snapshotSuffix;
		properties.setProperty(newKey, Boolean.toString(v.isSnapshot()));
		return properties;
	}

	public String getMajorSuffix() {
		return majorSuffix;
	}

	public void setMajorSuffix(String majorSuffix) {
		this.majorSuffix = majorSuffix;
	}

	public String getMinorSuffix() {
		return minorSuffix;
	}

	public void setMinorSuffix(String minorSuffix) {
		this.minorSuffix = minorSuffix;
	}

	public String getIncrementalSuffix() {
		return incrementalSuffix;
	}

	public void setIncrementalSuffix(String incrementalSuffix) {
		this.incrementalSuffix = incrementalSuffix;
	}

	public String getQualifierSuffix() {
		return qualifierSuffix;
	}

	public void setQualifierSuffix(String qualifierSuffix) {
		this.qualifierSuffix = qualifierSuffix;
	}

	public String getTrimmedSuffix() {
		return trimmedSuffix;
	}

	public void setTrimmedSuffix(String trimmedSuffix) {
		this.trimmedSuffix = trimmedSuffix;
	}

	public String getSnapshotSuffix() {
		return snapshotSuffix;
	}

	public void setSnapshotSuffix(String snapshotSuffix) {
		this.snapshotSuffix = snapshotSuffix;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
