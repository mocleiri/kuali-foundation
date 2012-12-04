package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class OrgProcessor implements PropertyProcessor {

	String groupId;
	String groupIdKey;
	String groupCodeKey;
	String projectGroupId;
	String projectGroupIdKey;
	String projectGroupCodeKey;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	@Override
	public void process(Properties properties) {
		String organizationCode = getOrgCode(groupId);
		String groupCode = getGroupCode(groupId, projectGroupId);

		PropertyUtils.addOrOverrideProperty(properties, groupIdKey, groupId, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupCodeKey, organizationCode, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, projectGroupIdKey, projectGroupId, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, projectGroupCodeKey, groupCode, propertyOverwriteMode);
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

	protected String getGroupCode(String orgId, String groupId) {
		if (!StringUtils.startsWith(groupId, orgId)) {
			throw new IllegalArgumentException(groupId + " does not start with " + orgId);
		}
		String groupCode = StringUtils.remove(groupId, orgId);
		if (StringUtils.startsWith(groupCode, ".")) {
			groupCode = StringUtils.substring(groupCode, 1);
		}
		int pos = StringUtils.indexOf(groupCode, ".");
		if (pos != -1) {
			groupCode = StringUtils.substring(groupCode, 0, pos);
		}
		return groupCode;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String organizationId) {
		this.groupId = organizationId;
	}

	public String getProjectGroupId() {
		return projectGroupId;
	}

	public void setProjectGroupId(String projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	public String getGroupIdKey() {
		return groupIdKey;
	}

	public void setGroupIdKey(String organizationIdKey) {
		this.groupIdKey = organizationIdKey;
	}

	public String getGroupCodeKey() {
		return groupCodeKey;
	}

	public void setGroupCodeKey(String organizationCodeKey) {
		this.groupCodeKey = organizationCodeKey;
	}

	public String getProjectGroupIdKey() {
		return projectGroupIdKey;
	}

	public void setProjectGroupIdKey(String groupIdKey) {
		this.projectGroupIdKey = groupIdKey;
	}

	public String getProjectGroupCodeKey() {
		return projectGroupCodeKey;
	}

	public void setProjectGroupCodeKey(String groupCodeKey) {
		this.projectGroupCodeKey = groupCodeKey;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
