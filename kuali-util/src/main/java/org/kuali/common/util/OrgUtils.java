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
	 * Given <code>org.kuali</code> and <code>org.kuali.rice</code> return <code>rice</code><br>
	 * Given <code>org.kuali</code> and <code>org.kuali.student.web</code> return <code>student</code><br>
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

}
