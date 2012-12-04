package org.kuali.common.util.property.processor;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.springframework.util.Assert;

public class HomeProcessor implements PropertyProcessor {
	private static final String FS = File.separator;

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	String userHomeKey = Constants.DEFAULT_USER_HOME_KEY;
	GlobalPropertiesMode globalPropertiesOverrideMode = GlobalPropertiesMode.BOTH;

	String orgGroupIdKey;
	String projectGroupIdKey;

	String codeSuffix = Constants.DEFAULT_CODE_SUFFIX;
	String homeSuffix = Constants.DEFAULT_HOME_SUFFIX;

	String orgGroupCodeKey;
	String projectGroupCodeKey;
	String orgHomeKey;
	String groupHomeKey = Constants.DEFAULT_GROUP_HOME_PREFIX + "." + Constants.DEFAULT_HOME_SUFFIX;

	public HomeProcessor() {
		this(null, null, GlobalPropertiesMode.BOTH);
	}

	public HomeProcessor(String orgGroupIdKey, String projectGroupIdKey, GlobalPropertiesMode globalPropertiesOverrideMode) {
		super();
		this.orgGroupIdKey = orgGroupIdKey;
		this.projectGroupIdKey = projectGroupIdKey;

		this.orgGroupCodeKey = orgGroupIdKey + "." + codeSuffix;
		this.projectGroupCodeKey = projectGroupIdKey + "." + codeSuffix;
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
	}

	@Override
	public void process(Properties properties) {
		Assert.notNull(userHomeKey);
		Assert.notNull(orgGroupCodeKey);
		Assert.notNull(projectGroupCodeKey);
		Properties duplicate = PropertyUtils.getProperties(properties, globalPropertiesOverrideMode);

		String userHome = duplicate.getProperty(userHomeKey);
		String orgGroupCode = duplicate.getProperty(orgGroupCodeKey);
		String projectGroupCode = duplicate.getProperty(projectGroupCodeKey);

		if (userHome == null || orgGroupCode == null || projectGroupCode == null) {
			return;
		}

		String orgHome = userHome + FS + "." + orgGroupCode;
		String groupHome = orgHome + FS + projectGroupCode;

		if (orgHomeKey == null) {
			this.orgHomeKey = orgGroupCode + ".home";
		}
		if (groupHomeKey == null) {
			this.groupHomeKey = orgGroupCode + "." + projectGroupCode + ".home";
		}

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

	public String getOrgGroupIdKey() {
		return orgGroupIdKey;
	}

	public void setOrgGroupIdKey(String orgGroupIdKey) {
		this.orgGroupIdKey = orgGroupIdKey;
	}

	public String getProjectGroupIdKey() {
		return projectGroupIdKey;
	}

	public void setProjectGroupIdKey(String projectGroupIdKey) {
		this.projectGroupIdKey = projectGroupIdKey;
	}

	public String getCodeSuffix() {
		return codeSuffix;
	}

	public void setCodeSuffix(String codeSuffix) {
		this.codeSuffix = codeSuffix;
	}

	public String getHomeSuffix() {
		return homeSuffix;
	}

	public void setHomeSuffix(String homeSuffix) {
		this.homeSuffix = homeSuffix;
	}

	public GlobalPropertiesMode getGlobalPropertiesOverrideMode() {
		return globalPropertiesOverrideMode;
	}

	public void setGlobalPropertiesOverrideMode(GlobalPropertiesMode globalPropertiesOverrideMode) {
		this.globalPropertiesOverrideMode = globalPropertiesOverrideMode;
	}

}
