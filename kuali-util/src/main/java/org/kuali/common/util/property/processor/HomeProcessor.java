package org.kuali.common.util.property.processor;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
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
	String userHomeKey = "user.home";
	String propertiesFileSuffix = ".properties";
	String organizationGroupId;
	String groupId;
	String artifactId;
	PropertyPlaceholderHelper helper;

	public HomeProcessor() {
		this(new PropertyPlaceholderHelper(Constants.DEFAULT_PLACEHOLDER_PREFIX, Constants.DEFAULT_PLACEHOLDER_SUFFIX));
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
		String originalValue = global.getProperty(userHomeKey);
		String resolvedValue = helper.replacePlaceholders(originalValue, global);
		if (!resolvedValue.equals(originalValue)) {
			logger.debug("Resolved '" + userHomeKey + "' [{}] -> [{}]", Str.flatten(originalValue), Str.flatten(resolvedValue));
		}
	}

	protected String getOrganizationCode(String organizationGroupId) {
		int pos = organizationGroupId.lastIndexOf(".");
		String organizationCode = organizationGroupId;
		if (pos != -1) {
			pos++;
			organizationCode = StringUtils.substring(organizationCode, pos);
		}
		return organizationCode;
	}

	protected String getGroupCode(String organizationGroupId, String groupId) {
		String groupCode = StringUtils.remove(groupId, organizationGroupId);
		if (StringUtils.startsWith(groupCode, ".")) {
			groupCode = StringUtils.substring(groupCode, 1);
		}
		int pos = StringUtils.indexOf(groupCode, ".");
		if (pos != -1) {
			groupCode = StringUtils.substring(groupCode, 0, pos);
		}
		return groupCode;
	}

	protected String getOrganizationHome(String userHome, String organizationGroupId) {
		String organizationCode = getOrganizationCode(organizationGroupId);
		StringBuilder sb = new StringBuilder();
		sb.append(userHome);
		sb.append(FS);
		sb.append(".");
		sb.append(organizationCode);
		return sb.toString();
	}

	protected String getGroupHome(String organizationHome, String organizationGroupId, String groupId) {
		return organizationHome + FS + getGroupCode(organizationGroupId, groupId);
	}

	protected String getPropertiesFilename(String groupHome, String artifactId, String propertiesFileSuffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(groupHome);
		sb.append(FS);
		sb.append(artifactId + propertiesFileSuffix);
		return sb.toString();
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

	public String getOrganizationGroupId() {
		return organizationGroupId;
	}

	public void setOrganizationGroupId(String organizationGroupId) {
		this.organizationGroupId = organizationGroupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

}
