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

import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.OrgUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.maven.MavenConstants;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class OrgProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(OrgProcessor.class);

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	String organizationGroupCodeSuffix = Constants.GROUP_ID + "." + Constants.DEFAULT_CODE_SUFFIX;
	String groupCodeProperty = MavenConstants.GROUP_ID_KEY + "." + Constants.DEFAULT_CODE_SUFFIX;
	String organizationGroupId;
	String groupId;

	public OrgProcessor() {
		this(null, null);
	}

	public OrgProcessor(String organizationGroupId, String groupId) {
		super();
		this.organizationGroupId = organizationGroupId;
		this.groupId = groupId;
	}

	@Override
	public void process(Properties properties) {
		logger.debug("organizationGroupId={}", organizationGroupId);
		logger.debug("groupId={}", groupId);

		Assert.notNull(organizationGroupId, "organizationGroupId is null");
		Assert.notNull(groupId, "groupId is null");

		String organizationCode = OrgUtils.getOrgCode(organizationGroupId);
		String groupCode = OrgUtils.getGroupCode(organizationGroupId, groupId);

		String organizationGroupCodeProperty = organizationCode + "." + organizationGroupCodeSuffix;

		PropertyUtils.addOrOverrideProperty(properties, organizationGroupCodeProperty, organizationCode, propertyOverwriteMode);
		PropertyUtils.addOrOverrideProperty(properties, groupCodeProperty, groupCode, propertyOverwriteMode);
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

	public String getOrganizationGroupCodeSuffix() {
		return organizationGroupCodeSuffix;
	}

	public void setOrganizationGroupCodeSuffix(String organizationGroupCodeSuffix) {
		this.organizationGroupCodeSuffix = organizationGroupCodeSuffix;
	}

}
