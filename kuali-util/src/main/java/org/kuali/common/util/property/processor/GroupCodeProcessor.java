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

public class GroupCodeProcessor implements PropertyProcessor {

	String organizationGroupIdProperty;
	String groupIdProperty;
	String groupCodeProperty;

	String codeSuffix = Constants.DEFAULT_CODE_SUFFIX;
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	public GroupCodeProcessor() {
		this(null, null);
	}

	public GroupCodeProcessor(String organizationGroupIdProperty, String groupIdProperty) {
		super();
		this.organizationGroupIdProperty = organizationGroupIdProperty;
		this.groupIdProperty = groupIdProperty;
		this.groupCodeProperty = groupIdProperty + "." + Constants.DEFAULT_CODE_SUFFIX;
		this.propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	}

	@Override
	public void process(Properties properties) {
		Assert.notNull(organizationGroupIdProperty, "organizationGroupIdProperty is null");
		Assert.notNull(groupIdProperty, "groupIdProperty is null");

		String organizationGroupId = properties.getProperty(organizationGroupIdProperty);
		String groupId = properties.getProperty(groupIdProperty);

		String groupCode = getGroupCode(organizationGroupId, groupId);

		PropertyUtils.addOrOverrideProperty(properties, groupCodeProperty, groupCode, propertyOverwriteMode);
	}

	/**
	 * Given <code>org.kuali</code> and <code>org.kuali.rice</code> return <code>rice</code><br>
	 * Given <code>org.kuali</code> and <code>org.kuali.student.web</code> return <code>student</code><br>
	 * Given <code>commons-lang</code> and <code>commons-lang</code> return <code>commons-lang</code><br>
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

	public String getOrganizationGroupIdProperty() {
		return organizationGroupIdProperty;
	}

	public void setOrganizationGroupIdProperty(String organizationGroupIdProperty) {
		this.organizationGroupIdProperty = organizationGroupIdProperty;
	}

	public String getGroupIdProperty() {
		return groupIdProperty;
	}

	public void setGroupIdProperty(String groupIdProperty) {
		this.groupIdProperty = groupIdProperty;
	}

	public String getGroupCodeProperty() {
		return groupCodeProperty;
	}

	public void setGroupCodeProperty(String groupCodeProperty) {
		this.groupCodeProperty = groupCodeProperty;
	}

	public String getCodeSuffix() {
		return codeSuffix;
	}

	public void setCodeSuffix(String codeSuffix) {
		this.codeSuffix = codeSuffix;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
