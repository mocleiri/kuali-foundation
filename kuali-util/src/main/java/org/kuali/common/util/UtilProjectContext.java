package org.kuali.common.util;

public class UtilProjectContext extends DefaultProjectContext {

	private static ProjectContext instance = new UtilProjectContext();

	private UtilProjectContext() {
		super("kuali-util");
	}

	public static ProjectContext getInstance() {
		return instance;
	}

}