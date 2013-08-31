package org.kuali.common.util.project;

import org.kuali.common.util.Assert;

public enum Groups {

	COMMON(KualiProjectConstants.COMMON_GROUP_ID), //
	RICE(KualiProjectConstants.RICE_GROUP_ID), //
	STUDENT(KualiProjectConstants.STUDENT_GROUP_ID), //
	MOBILITY(KualiProjectConstants.MOBILITY_GROUP_ID), //
	OLE(KualiProjectConstants.OLE_GROUP_ID), //
	KPME(KualiProjectConstants.KPME_GROUP_ID);

	private Groups(String groupId) {
		Assert.noBlanks(groupId);
		this.groupId = groupId;
	}

	private final String groupId;

	public String getGroupId() {
		return groupId;
	}

}
