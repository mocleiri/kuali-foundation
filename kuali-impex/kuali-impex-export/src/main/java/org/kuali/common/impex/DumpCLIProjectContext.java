package org.kuali.common.impex;

import org.kuali.common.util.DefaultProjectContext;

public class DumpCLIProjectContext extends DefaultProjectContext {

	public static final String ARTIFACT_ID = "kuali-impex-export";
	private static final String LOCATION = "${user.home}/.impex/dump.properties";

	public DumpCLIProjectContext() {
		super(ARTIFACT_ID, LOCATION);
	}

}
