package org.kuali.common.deploy.project;

import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;

public abstract class DeployProjectConstants {

	private static final String GROUP_ID = KualiProjectConstants.COMMON_GROUP_ID;

	public static final ProjectIdentifier ID = new ProjectIdentifier(GROUP_ID, "kuali-deploy");

}
