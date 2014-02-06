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
package org.kuali.common.util;

import org.apache.commons.lang3.StringUtils;

public class OrgUtils {

	/**
	 * Given {@code org.kuali} return {@code kuali}
	 */
	public static final String getOrgCode(String organizationGroupId) {
		int pos = StringUtils.lastIndexOf(organizationGroupId, ".");
		if (pos == -1) {
			return organizationGroupId;
		} else {
			return StringUtils.substring(organizationGroupId, pos + 1);
		}
	}

	/**
	 * Given {@code org.kuali} and {@code org.kuali.rice} return {@code rice}<br>
	 * Given {@code org.kuali} and {@code org.kuali.student.web} return {@code student}<br>
	 */
	public static final String getGroupCode(String organizationGroupId, String groupId) {
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

	/**
	 * Given {@code org.kuali} and {@code org.kuali.student.web} return {@code org.kuali.student}<br>
	 */
	public static final String getGroupBase(String organizationGroupId, String groupId) {
		return organizationGroupId + "." + getGroupCode(organizationGroupId, groupId);
	}

}
