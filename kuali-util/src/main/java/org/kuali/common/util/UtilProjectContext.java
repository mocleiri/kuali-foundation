package org.kuali.common.util;

public class UtilProjectContext extends DefaultProjectContext {

	private static ProjectContext instance = new UtilProjectContext();

	private UtilProjectContext() {
		super(ProjectUtils.KUALI_COMMON_GROUP_ID, "kuali-util");
	}

	public static ProjectContext getInstance() {
		return instance;
	}

}