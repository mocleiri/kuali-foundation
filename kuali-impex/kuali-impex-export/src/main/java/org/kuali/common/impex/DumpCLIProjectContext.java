package org.kuali.common.impex;

import java.util.Arrays;

import org.kuali.common.util.DefaultProjectContext;

public class DumpCLIProjectContext extends DefaultProjectContext {

	public static final String ARTIFACT_ID = DumpProjectContext.ARTIFACT_ID;
	private static final String LOCATION = "${user.home}/.impex/dump.properties";

	public DumpCLIProjectContext() {
		super(ARTIFACT_ID, Arrays.asList(LOCATION));
	}
}
