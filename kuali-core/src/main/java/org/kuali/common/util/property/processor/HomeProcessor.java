/**
 * Copyright 2010-2013 The Kuali Foundation
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

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.OrgUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.springframework.util.Assert;

public class HomeProcessor implements PropertyProcessor {

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	String userHomeProperty = Constants.DEFAULT_USER_HOME_PROPERTY;
	String fileSeparator = File.separator;
	String hiddenDirectoryIndicator = Str.DOT;
	String organizationGroupId;
	String groupId;

	public HomeProcessor() {
		this(null, null);
	}

	public HomeProcessor(String organizationGroupId, String groupId) {
		super();
		this.organizationGroupId = organizationGroupId;
		this.groupId = groupId;
	}

	@Override
	public void process(Properties properties) {
		Assert.notNull(organizationGroupId);
		Assert.notNull(groupId);

		String organizationCode = OrgUtils.getOrgCode(organizationGroupId);
		String groupCode = OrgUtils.getGroupCode(organizationGroupId, groupId);

		String organizationHomeProperty = organizationCode + Str.DOT + Constants.DEFAULT_HOME_SUFFIX;
		String groupHomeProperty = organizationCode + Str.DOT + Constants.GROUP + Str.DOT + Constants.DEFAULT_HOME_SUFFIX;

		String organizationHome = System.getProperty(userHomeProperty) + fileSeparator + hiddenDirectoryIndicator + organizationCode;
		String groupHome = organizationHome + fileSeparator + groupCode;

		PropertyUtils.addOrOverrideProperty(properties, organizationHomeProperty, organizationHome, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupHomeProperty, groupHome, propertyOverwriteMode);
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	public String getUserHomeProperty() {
		return userHomeProperty;
	}

	public void setUserHomeProperty(String userHomeProperty) {
		this.userHomeProperty = userHomeProperty;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public String getHiddenDirectoryIndicator() {
		return hiddenDirectoryIndicator;
	}

	public void setHiddenDirectoryIndicator(String hiddenDirectoryIndicator) {
		this.hiddenDirectoryIndicator = hiddenDirectoryIndicator;
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
}
