/**
 * Copyright 2010-2012 The Kuali Foundation
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

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.util.Assert;

public class OrgProcessor implements PropertyProcessor {

	String orgGroupIdKey;
	String projectGroupIdKey;

	String orgGroupCodeKey;
	String projectGroupCodeKey;

	String codeSuffix = Constants.DEFAULT_CODE_SUFFIX;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public OrgProcessor() {
		this(null, null);
	}

	public OrgProcessor(String orgGroupIdKey, String projectGroupIdKey) {
		super();
		this.orgGroupIdKey = orgGroupIdKey;
		this.projectGroupIdKey = projectGroupIdKey;
		this.orgGroupCodeKey = orgGroupIdKey + "." + codeSuffix;
		this.projectGroupCodeKey = projectGroupIdKey + "." + codeSuffix;
		this.propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	}

	@Override
	public void process(Properties properties) {
		Assert.notNull(orgGroupIdKey, "orgGroupIdKey is null");
		Assert.notNull(projectGroupIdKey, "projectGroupIdKey is null");

		String orgGroupId = properties.getProperty(orgGroupIdKey);
		String projectGroupId = properties.getProperty(projectGroupIdKey);

		if (orgGroupId == null || projectGroupId == null) {
			return;
		}

		String orgGroupCode = getOrgGroupCode(orgGroupId);
		String projectGroupCode = getProjectGroupCode(orgGroupId, projectGroupId);

		PropertyUtils.addOrOverrideProperty(properties, orgGroupCodeKey, orgGroupCode, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, projectGroupCodeKey, projectGroupCode, propertyOverwriteMode);
	}

	protected String getOrgGroupCode(String groupId) {
		int pos = StringUtils.lastIndexOf(groupId, ".");
		String code = groupId;
		if (pos != -1) {
			pos++;
			code = StringUtils.substring(code, pos);
		}
		return code;
	}

	protected String getProjectGroupCode(String orgGroupId, String projectGroupId) {
		if (!StringUtils.startsWith(projectGroupId, orgGroupId)) {
			throw new IllegalArgumentException(projectGroupId + " does not start with " + orgGroupId);
		}
		String code = StringUtils.remove(projectGroupId, orgGroupId);
		if (StringUtils.startsWith(code, ".")) {
			code = StringUtils.substring(code, 1);
		}
		int pos = StringUtils.indexOf(code, ".");
		if (pos != -1) {
			code = StringUtils.substring(code, 0, pos);
		}
		return code;
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

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	public String getCodeSuffix() {
		return codeSuffix;
	}

	public void setCodeSuffix(String codeSuffix) {
		this.codeSuffix = codeSuffix;
	}

}
