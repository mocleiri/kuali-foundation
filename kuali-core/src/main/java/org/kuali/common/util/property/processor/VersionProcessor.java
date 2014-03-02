/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.property.processor;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.kuali.common.util.property.Constants;

public class VersionProcessor implements PropertyProcessor {

	public static final String SANITIZED_QUALIFIER = Constants.DEFAULT_QUALIFIER_VERSION_SUFFIX + ".sanitized";
	public static final String SANITIZED_VERSION_KEY = "project.version.sanitized";

	String majorSuffix = Constants.DEFAULT_MAJOR_VERSION_SUFFIX;
	String minorSuffix = Constants.DEFAULT_MINOR_VERSION_SUFFIX;
	String incrementalSuffix = Constants.DEFAULT_INCREMENTAL_VERSION_SUFFIX;
	String qualifierSuffix = Constants.DEFAULT_QUALIFIER_VERSION_SUFFIX;
	String sanitizedQualifierSuffix = SANITIZED_QUALIFIER;
	String trimmedSuffix = Constants.DEFAULT_TRIMMED_VERSION_SUFFIX;
	String snapshotSuffix = Constants.DEFAULT_SNAPSHOT_VERSION_SUFFIX;
	boolean alwaysAddOrOverride;

	List<String> includes;
	List<String> excludes;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public VersionProcessor() {
		this(Collections.<String> emptyList());
	}

	public VersionProcessor(String include) {
		this(Collections.singletonList(include));
	}

	public VersionProcessor(List<String> includes) {
		this(includes, false);
	}

	public VersionProcessor(List<String> includes, boolean alwaysAddOrOverride) {
		this.includes = includes;
		this.alwaysAddOrOverride = alwaysAddOrOverride;
	}

	@Override
	public void process(Properties properties) {
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
			if (alwaysAddOrOverride) {
				properties.setProperty(versionKey, versionValue);
			} else {
				PropertyUtils.addOrOverrideProperty(properties, versionKey, versionValue, propertyOverwriteMode);
			}
		}
	}

	public Properties getVersionProperties(String key, Version v) {
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
			String sanitizedKey = key + "." + sanitizedQualifierSuffix;
			String sanitizedValue = VersionUtils.getSanitizedQualifier(v.getQualifier());
			properties.setProperty(sanitizedKey, sanitizedValue);
		}
		if (v.getTrimmed() != null) {
			String newKey = key + "." + trimmedSuffix;
			properties.setProperty(newKey, v.getTrimmed());
		}
		String newKey = key + "." + snapshotSuffix;
		properties.setProperty(newKey, Boolean.toString(v.isSnapshot()));
		properties.setProperty(SANITIZED_VERSION_KEY, VersionUtils.asSanitizedString(v));
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

	public boolean isAlwaysAddOrOverride() {
		return alwaysAddOrOverride;
	}

	public void setAlwaysAddOrOverride(boolean alwaysAddOrOverride) {
		this.alwaysAddOrOverride = alwaysAddOrOverride;
	}

	public String getSanitizedQualifierSuffix() {
		return sanitizedQualifierSuffix;
	}

	public void setSanitizedQualifierSuffix(String sanitizedQualifierSuffix) {
		this.sanitizedQualifierSuffix = sanitizedQualifierSuffix;
	}

}
