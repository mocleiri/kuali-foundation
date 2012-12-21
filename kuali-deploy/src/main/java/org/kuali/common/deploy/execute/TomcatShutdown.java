package org.kuali.common.deploy.execute;

import org.kuali.common.util.Mode;

public class TomcatShutdown extends RemoteShellScript {

	public TomcatShutdown() {
		this(null);
	}

	public TomcatShutdown(String hostname) {
		super();
		this.hostname = hostname;
		this.login = Constants.TOMCAT_USER;
		this.script = Constants.TOMCAT_SHUTDOWN;
		this.nonZeroExitValueMode = Mode.WARN;
	}
}
