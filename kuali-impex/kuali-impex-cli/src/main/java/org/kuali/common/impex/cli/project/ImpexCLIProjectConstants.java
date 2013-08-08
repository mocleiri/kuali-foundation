package org.kuali.common.impex.cli.project;

import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;

public abstract class ImpexCLIProjectConstants {

	// These 2 must always exactly match what is in the Maven pom.xml
	private static final String GID = KualiProjectConstants.COMMON_GROUP_ID;
	private static final String AID = "kuali-impex-cli";

	public static final ProjectIdentifier PROJECT_ID = new ProjectIdentifier(GID, AID);

}
