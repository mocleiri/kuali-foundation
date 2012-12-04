package org.kuali.common.util.property.processor;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

public class HomeProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(HomeProcessor.class);
	private static final String FS = File.separator;

	GlobalPropertiesMode globalPropertiesMode = GlobalPropertiesMode.BOTH;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	String userHomeKey = Constants.DEFAULT_USER_HOME_KEY;
	String orgCodeKey;
	String groupCodeKey;
	String orgHomeKey;
	String groupHomeKey;

	public HomeProcessor() {
		this(Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER);
	}

	public HomeProcessor(PropertyPlaceholderHelper helper) {
		this(helper, GlobalPropertiesMode.BOTH);
	}

	public HomeProcessor(PropertyPlaceholderHelper helper, GlobalPropertiesMode globalPropertiesMode) {
		super();
		this.helper = helper;
		this.globalPropertiesMode = globalPropertiesMode;
	}

	@Override
	public void process(Properties properties) {
		Properties global = PropertyUtils.getProperties(properties, globalPropertiesMode);
		String userHome = getResolvedValue(userHomeKey, global, helper);
		String orgCode = getResolvedValue(orgCodeKey, global, helper);
		String groupCode = getResolvedValue(groupCodeKey, global, helper);

		String organizationHome = userHome + FS + "." + orgCode;
		String groupHome = organizationHome + FS + groupCode;

		PropertyUtils.addOrOverrideProperty(properties, orgHomeKey, organizationHome, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupHomeKey, groupHome, propertyOverwriteMode);
	}

	protected String getResolvedValue(String key, Properties properties, PropertyPlaceholderHelper helper) {
		String original = properties.getProperty(key);
		String resolved = helper.replacePlaceholders(original, properties);
		if (!resolved.equals(original)) {
			logger.debug("Resolved '" + key + "' [{}] -> [{}]", Str.flatten(original), Str.flatten(resolved));
		}
		return resolved;
	}

	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	public String getUserHomeKey() {
		return userHomeKey;
	}

	public void setUserHomeKey(String userHomeKey) {
		this.userHomeKey = userHomeKey;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public String getOrgCodeKey() {
		return orgCodeKey;
	}

	public void setOrgCodeKey(String organizationCodeKey) {
		this.orgCodeKey = organizationCodeKey;
	}

	public String getGroupCodeKey() {
		return groupCodeKey;
	}

	public void setGroupCodeKey(String groupCodeKey) {
		this.groupCodeKey = groupCodeKey;
	}

	public String getOrgHomeKey() {
		return orgHomeKey;
	}

	public void setOrgHomeKey(String organizationHomeKey) {
		this.orgHomeKey = organizationHomeKey;
	}

	public String getGroupHomeKey() {
		return groupHomeKey;
	}

	public void setGroupHomeKey(String groupHomeKey) {
		this.groupHomeKey = groupHomeKey;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
