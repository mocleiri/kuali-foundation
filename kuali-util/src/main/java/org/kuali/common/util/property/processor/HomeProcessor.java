package org.kuali.common.util.property.processor;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class HomeProcessor implements PropertyProcessor {
	private static final String FS = File.separator;

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	String userHomeKey = Constants.DEFAULT_USER_HOME_KEY;
	String orgGroupCodeKey;
	String projectGroupCodeKey;
	String orgHomeKey;
	String groupHomeKey;

	@Override
	public void process(Properties properties) {
		String userHome = properties.getProperty(userHomeKey);
		String orgGroupCode = properties.getProperty(orgGroupCodeKey);
		String projectGroupCode = properties.getProperty(projectGroupCodeKey);

		String orgHome = userHome + FS + "." + orgGroupCode;
		String groupHome = orgHome + FS + projectGroupCode;

		PropertyUtils.addOrOverrideProperty(properties, orgHomeKey, orgHome, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupHomeKey, groupHome, propertyOverwriteMode);
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	public String getUserHomeKey() {
		return userHomeKey;
	}

	public void setUserHomeKey(String userHomeKey) {
		this.userHomeKey = userHomeKey;
	}

	public String getOrgGroupCodeKey() {
		return orgGroupCodeKey;
	}

	public void setOrgGroupCodeKey(String orgGroupCodeKey) {
		this.orgGroupCodeKey = orgGroupCodeKey;
	}

	public String getProjectGroupCodeKey() {
		return projectGroupCodeKey;
	}

	public void setProjectGroupCodeKey(String projectGroupCodeKey) {
		this.projectGroupCodeKey = projectGroupCodeKey;
	}

	public String getOrgHomeKey() {
		return orgHomeKey;
	}

	public void setOrgHomeKey(String orgHomeKey) {
		this.orgHomeKey = orgHomeKey;
	}

	public String getGroupHomeKey() {
		return groupHomeKey;
	}

	public void setGroupHomeKey(String groupHomeKey) {
		this.groupHomeKey = groupHomeKey;
	}

}
