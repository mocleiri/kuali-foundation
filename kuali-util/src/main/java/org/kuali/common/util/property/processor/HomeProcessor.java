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
		String originalUserHome = global.getProperty(userHomeKey);
		String resolvedUserHome = helper.replacePlaceholders(originalUserHome, global);
		if (!resolvedUserHome.equals(originalUserHome)) {
			logger.debug("Resolved '" + userHomeKey + "' [{}] -> [{}]", Str.flatten(originalUserHome), Str.flatten(resolvedUserHome));
		}
		AppConfig ac = getAppConfig(resolvedUserHome, organizationGroupId, groupId, artifactId, propertiesFileSuffix);
		Properties newProperties = getAppProperties(ac);
		properties.putAll(newProperties);
	}

	protected Properties getAppProperties(AppConfig ac) {
		String orgIdKey = "org.id";
		String orgCodeKey = "org.code";
		String kualiHomeKey = ac.getOrgCode() + ".home";
		String kualiGroupKey = ac.getOrgCode() + ".group";
		String groupHomeKey = ac.getOrgCode() + "." + ac.getGroupCode() + ".home";

		Properties properties = new Properties();
		properties.setProperty(kualiHomeKey, ac.getOrgHome());
		properties.setProperty(groupHomeKey, ac.getGroupHome());
		properties.setProperty(kualiGroupKey, ac.getGroupCode());
		properties.setProperty(orgIdKey, organizationGroupId);
		properties.setProperty(orgCodeKey, ac.getOrgCode());
		return properties;
	}

	protected AppConfig getAppConfig(String resolvedUserHome, String organizationGroupId, String groupId, String artifactId, String propertiesFileSuffix) {
		String orgCode = getOrgCode(organizationGroupId);
		String orgHome = getOrgHome(resolvedUserHome, orgCode);
		String orgPropertiesLocation = getPropertiesFileLocation(orgHome, orgCode, propertiesFileSuffix);

		String groupCode = getGroupCode(organizationGroupId, groupId);
		String groupHome = getGroupHome(orgHome, groupCode);
		String groupPropertiesLocation = getPropertiesFileLocation(groupHome, groupCode, propertiesFileSuffix);

		String appCode = artifactId;
		String appHome = getAppHome(groupHome, appCode);
		String appPropertiesLocation = getPropertiesFileLocation(appHome, appCode, propertiesFileSuffix);

		AppConfig ac = new AppConfig();

		ac.setOrgCode(orgCode);
		ac.setOrgHome(orgHome);
		ac.setOrgPropertiesLocation(orgPropertiesLocation);

		ac.setGroupCode(groupCode);
		ac.setGroupHome(groupHome);
		ac.setGroupPropertiesLocation(groupPropertiesLocation);

		ac.setAppCode(appCode);
		ac.setAppHome(appHome);
		ac.setAppPropertiesLocation(appPropertiesLocation);

		return ac;
	}

	protected String getOrgCode(String organizationGroupId) {
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

	protected String getOrgHome(String userHome, String orgCode) {
		StringBuilder sb = new StringBuilder();
		sb.append(userHome);
		sb.append(FS);
		sb.append(".");
		sb.append(orgCode);
		return sb.toString();
	}

	protected String getGroupHome(String orgHome, String groupCode) {
		StringBuilder sb = new StringBuilder();
		sb.append(orgHome);
		sb.append(FS);
		sb.append(groupCode);
		return sb.toString();
	}

	protected String getAppHome(String groupHome, String artifactId) {
		StringBuilder sb = new StringBuilder();
		sb.append(groupHome);
		sb.append(FS);
		sb.append(artifactId);
		return sb.toString();
	}

	protected String getPropertiesFileLocation(String home, String code, String suffix) {
		StringBuilder sb = new StringBuilder();
		sb.append(home);
		sb.append(FS);
		sb.append(code);
		sb.append(suffix);
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

	public String getPropertiesFileSuffix() {
		return propertiesFileSuffix;
	}

	public void setPropertiesFileSuffix(String propertiesFileSuffix) {
		this.propertiesFileSuffix = propertiesFileSuffix;
	}

}
