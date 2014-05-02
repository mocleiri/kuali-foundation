package org.kuali.common.deploy.project;

import org.kuali.common.util.project.model.KualiGroup;
import org.kuali.common.util.project.model.ProjectIdentifier;

public abstract class DeployProjectConstants {

	public static final ProjectIdentifier ID = new ProjectIdentifier(KualiGroup.COMMON.getId(), "kuali-deploy");

}
