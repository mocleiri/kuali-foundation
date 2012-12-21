package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class TomcatCleanup extends TomcatBase implements Executable {

	String script = Constants.TOMCAT_BIN + "/cleanup.sh";

	public TomcatCleanup() {
		this(null);
	}

	public TomcatCleanup(String hostname) {
		super();
		this.hostname = hostname;
	}

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, tomcatBin + "/" + getScript());
		UnixUtils.validate(exitValue, "Tomcat script [" + getScript() + "] exit value is [" + exitValue + "]", nonZeroExitValueMode);
	}

	@Override
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
