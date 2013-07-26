package org.kuali.common.util.project;

public abstract class KualiUtilProjectConstants {

	// These 2 must always exactly match what is in the Maven pom
	private static final String GROUP_ID = KualiProjectConstants.COMMON_GROUP_ID;
	private static final String ARTIFACT_ID = "kuali-util";

	public static final ProjectIdentifier PROJECT_IDENTIFIER = new ImmutableProjectIdentifier(GROUP_ID, ARTIFACT_ID);

}
