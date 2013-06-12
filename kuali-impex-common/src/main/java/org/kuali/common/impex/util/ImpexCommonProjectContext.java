package org.kuali.common.impex.util;

import org.kuali.common.util.DefaultProjectContext;

public class ImpexCommonProjectContext extends DefaultProjectContext {

	// This has to exactly match the <artifactId> from the pom.xml
	private static final String ARTIFACT_ID = "kuali-impex-common";

	public ImpexCommonProjectContext() {
		super(ARTIFACT_ID);
	}

}
