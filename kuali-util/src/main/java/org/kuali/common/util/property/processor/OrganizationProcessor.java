package org.kuali.common.util.property.processor;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class OrganizationProcessor implements PropertyProcessor {

	String organizationId;
	String groupId;
	String organizationIdKey;
	String organizationCodeKey;
	String groupIdKey;
	String groupCodeKey;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	@Override
	public void process(Properties properties) {
		String organizationCode = getOrgCode(organizationId);
		String groupCode = getGroupCode(organizationId, groupId);

		PropertyUtils.addOrOverrideProperty(properties, organizationIdKey, organizationId, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, organizationCodeKey, organizationCode, propertyOverwriteMode);
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

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String projectGroupId) {
		this.groupId = projectGroupId;
	}

	public String getOrganizationIdKey() {
		return organizationIdKey;
	}

	public void setOrganizationIdKey(String organizationIdKey) {
		this.organizationIdKey = organizationIdKey;
	}

	public String getOrganizationCodeKey() {
		return organizationCodeKey;
	}

	public void setOrganizationCodeKey(String organizationCodeKey) {
		this.organizationCodeKey = organizationCodeKey;
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
