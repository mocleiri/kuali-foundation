package org.kuali.common.util;

public class UtilProjectContext extends AbstractProjectContext {

	private static ProjectContext instance = new UtilProjectContext();

	// This must exactly match the artifact id declared in the pom
	private static final String ARTIFACT_ID = "kuali-util";

	private UtilProjectContext() {
		super();
	}

	public static ProjectContext getInstance() {
		return instance;
	}

	@Override
	public String getArtifactId() {
		return "kuali-util";
	}

}