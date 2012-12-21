package org.kuali.common.deploy.execute;

import org.kuali.common.util.Mode;

public class TomcatStop extends TomcatBase {

	String script = "/usr/local/tomcat/bin/forced-shutdown.sh";

	public TomcatStop() {
		this(null);
	}

	public TomcatStop(String hostname) {
		super();
		this.hostname = hostname;
		this.nonZeroExitValueMode = Mode.WARN;
	}

	@Override
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
