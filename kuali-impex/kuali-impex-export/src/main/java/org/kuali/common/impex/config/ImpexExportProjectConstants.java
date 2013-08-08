package org.kuali.common.impex.config;

import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;

public abstract class ImpexExportProjectConstants {

	private static final String GID = KualiProjectConstants.COMMON_GROUP_ID;
	private static final String AID = "kuali-impex-export";

	public static final ProjectIdentifier PROJECT_ID = new ProjectIdentifier(GID, AID);

}
