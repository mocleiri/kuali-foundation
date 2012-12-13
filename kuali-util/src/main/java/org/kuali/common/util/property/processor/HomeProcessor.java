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

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.util.Assert;

public class HomeProcessor implements PropertyProcessor {

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	String fileSeparator = File.separator;
	String hiddenDirectoryIndicator = ".";
	String userHomeProperty = "user.home";
	String organizationHomeProperty;
	String groupHomeProperty;
	String organizationCode;
	String groupCode;

	public HomeProcessor() {
		this(null, null);
	}

	public HomeProcessor(String organizationCode, String groupCode) {
		super();
		this.organizationCode = organizationCode;
		this.groupCode = groupCode;
	}

	@Override
	public void process(Properties properties) {

		Assert.notNull(organizationCode, "organizationCode is null");
		Assert.notNull(groupCode, "groupCode is null");

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

	public String getFileSeparator() {
		return fileSeparator;
	}

	public void setFileSeparator(String fileSeparator) {
		this.fileSeparator = fileSeparator;
	}

	public String getUserHomeProperty() {
		return userHomeProperty;
	}

	public void setUserHomeProperty(String userHomeProperty) {
		this.userHomeProperty = userHomeProperty;
	}

	public String getOrganizationHomeProperty() {
		return organizationHomeProperty;
	}

	public void setOrganizationHomeProperty(String organizationHomeProperty) {
		this.organizationHomeProperty = organizationHomeProperty;
	}

	public String getGroupHomeProperty() {
		return groupHomeProperty;
	}

	public void setGroupHomeProperty(String groupHomeProperty) {
		this.groupHomeProperty = groupHomeProperty;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getHiddenDirectoryIndicator() {
		return hiddenDirectoryIndicator;
	}

	public void setHiddenDirectoryIndicator(String hiddenDirectoryIndicator) {
		this.hiddenDirectoryIndicator = hiddenDirectoryIndicator;
	}

}
