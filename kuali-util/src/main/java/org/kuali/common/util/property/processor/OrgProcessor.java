package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class OrgProcessor implements PropertyProcessor {

	String orgId;
	String groupId;
	String orgIdKey;
	String orgCodeKey;
	String groupIdKey;
	String groupCodeKey;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	@Override
	public void process(Properties properties) {
		String organizationCode = getOrgCode(orgId);
		String groupCode = getGroupCode(orgId, groupId);

		PropertyUtils.addOrOverrideProperty(properties, orgIdKey, orgId, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, orgCodeKey, organizationCode, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupIdKey, groupId, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupCodeKey, groupCode, propertyOverwriteMode);
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String organizationId) {
		this.orgId = organizationId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String projectGroupId) {
		this.groupId = projectGroupId;
	}

	public String getOrgIdKey() {
		return orgIdKey;
	}

	public void setOrgIdKey(String organizationIdKey) {
		this.orgIdKey = organizationIdKey;
	}

	public String getOrgCodeKey() {
		return orgCodeKey;
	}

	public void setOrgCodeKey(String organizationCodeKey) {
		this.orgCodeKey = organizationCodeKey;
	}

	public String getGroupIdKey() {
		return groupIdKey;
	}

	public void setGroupIdKey(String groupIdKey) {
		this.groupIdKey = groupIdKey;
	}

	public String getGroupCodeKey() {
		return groupCodeKey;
	}

	public void setGroupCodeKey(String groupCodeKey) {
		this.groupCodeKey = groupCodeKey;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
