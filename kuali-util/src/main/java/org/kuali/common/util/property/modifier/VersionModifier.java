package org.kuali.common.util.property.modifier;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.Mode;

public class VersionModifier implements PropertyModifier {

	public VersionModifier() {
		this(null);
	}

	public VersionModifier(List<String> includes) {
		super();
		this.includes = includes;
	}

	String majorSuffix = Constants.DEFAULT_MAJOR_VERSION_SUFFIX;
	String minorSuffix = Constants.DEFAULT_MINOR_VERSION_SUFFIX;
	String incrementalSuffix = Constants.DEFAULT_INCREMENTAL_VERSION_SUFFIX;
	String qualifierSuffix = Constants.DEFAULT_QUALIFIER_VERSION_SUFFIX;
	String trimmedSuffix = Constants.DEFAULT_TRIMMED_VERSION_SUFFIX;
	String snapshotSuffix = Constants.DEFAULT_SNAPSHOT_VERSION_SUFFIX;

	List<String> includes;
	List<String> excludes;
	Mode propertyOverwriteMode = Mode.INFORM;

	@Override
	public void modify(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String version = properties.getProperty(key);
			Version v = VersionUtils.getVersion(version);
			if (v.getMajor() != null) {
				String newKey = key + "." + majorSuffix;
				PropertyUtils.setProperty(properties, newKey, v.getMajor(), propertyOverwriteMode);
			}
			if (v.getMinor() != null) {
				String newKey = key + "." + minorSuffix;
				PropertyUtils.setProperty(properties, newKey, v.getMinor(), propertyOverwriteMode);
			}
			if (v.getMinor() != null) {
				String newKey = key + "." + minorSuffix;
				PropertyUtils.setProperty(properties, newKey, v.getMinor(), propertyOverwriteMode);
			}
			if (v.getIncremental() != null) {
				String newKey = key + "." + incrementalSuffix;
				PropertyUtils.setProperty(properties, newKey, v.getIncremental(), propertyOverwriteMode);
			}
			if (v.getQualifier() != null) {
				String newKey = key + "." + qualifierSuffix;
				PropertyUtils.setProperty(properties, newKey, v.getQualifier(), propertyOverwriteMode);
			}
			if (v.getTrimmed() != null) {
				String newKey = key + "." + trimmedSuffix;
				PropertyUtils.setProperty(properties, newKey, v.getTrimmed(), propertyOverwriteMode);
			}
			String newKey = key + "." + snapshotSuffix;
			PropertyUtils.setProperty(properties, newKey, Boolean.toString(v.isSnapshot()), propertyOverwriteMode);
		}
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
