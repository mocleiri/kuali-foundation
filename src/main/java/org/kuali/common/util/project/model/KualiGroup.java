package org.kuali.common.util.project.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.project.KualiProjectConstants;

public enum KualiGroup {

	COMMON(KualiProjectConstants.COMMON_GROUP_ID), //
	RICE(KualiProjectConstants.RICE_GROUP_ID), //
	STUDENT(KualiProjectConstants.STUDENT_GROUP_ID), //
	MOBILITY(KualiProjectConstants.MOBILITY_GROUP_ID), //
	OLE(KualiProjectConstants.OLE_GROUP_ID), //
	KFS(KualiProjectConstants.KFS_GROUP_ID), //
	COEUS(KualiProjectConstants.COEUS_GROUP_ID), //
	READY(KualiProjectConstants.READY_GROUP_ID), //
	KPME(KualiProjectConstants.KPME_GROUP_ID);

	private KualiGroup(String groupId) {
		Assert.noBlanks(groupId);
		this.id = groupId;
	}

	private final String id;

	public String getId() {
		return id;
	}

	/**
	 * Return the Kuali groups as an immutable list
	 */
	public static final List<KualiGroup> asList() {
		return Collections.unmodifiableList(Arrays.asList(values()));
	}

}
