package org.kuali.common.deploy.execute;

import org.kuali.common.util.Mode;

public class TomcatCleanup extends RemoteShellScript {

	public TomcatCleanup() {
		this(null);
	}

	public TomcatCleanup(String hostname) {
		super();
		this.hostname = hostname;
		this.login = Constants.TOMCAT_USER;
		this.script = Constants.TOMCAT_CLEANUP;
		this.nonZeroExitValueMode = Mode.WARN;
	}
}
