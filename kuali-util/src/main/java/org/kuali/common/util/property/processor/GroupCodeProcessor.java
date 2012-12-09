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

public class GroupCodeProcessor implements PropertyProcessor {

	String organizationGroupId;
	String groupId;
	String groupCodeProperty = Constants.DEFAULT_GROUP_ID_PROPERTY + "." + Constants.DEFAULT_CODE_SUFFIX;

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public GroupCodeProcessor() {
		this(null, null);
	}

	public GroupCodeProcessor(String organizationGroupId, String groupId) {
		super();
		this.organizationGroupId = organizationGroupId;
		this.groupId = groupId;
	}

	@Override
	public void process(Properties properties) {
		String groupCode = getGroupCode(organizationGroupId, groupId);
		PropertyUtils.addOrOverrideProperty(properties, groupCodeProperty, groupCode, propertyOverwriteMode);
	}

	/**
	 * Given <code>org.kuali</code> and <code>org.kuali.rice</code> return <code>rice</code><br>
	 * Given <code>org.kuali</code> and <code>org.kuali.student.web</code> return <code>student</code><br>
	 */
	protected String getGroupCode(String organizationGroupId, String groupId) {
		if (!StringUtils.startsWith(groupId, organizationGroupId)) {
			throw new IllegalArgumentException(groupId + " does not start with " + organizationGroupId);
		}
		String code = StringUtils.remove(groupId, organizationGroupId);
		if (StringUtils.startsWith(code, ".")) {
			code = StringUtils.substring(code, 1);
		}
		int pos = StringUtils.indexOf(code, ".");
		if (pos != -1) {
			code = StringUtils.substring(code, 0, pos);
		}
		return code;
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

	public String getGroupCodeProperty() {
		return groupCodeProperty;
	}

	public void setGroupCodeProperty(String groupCodeProperty) {
		this.groupCodeProperty = groupCodeProperty;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}
}
