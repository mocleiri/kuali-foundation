package org.kuali.common.deploy.execute;

import org.kuali.common.util.Mode;
import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public abstract class TomcatBase implements Executable {

	Mode nonZeroExitValueMode = Mode.WARN;
	String user = "root";
	String hostname;
	String login = "tomcat";

	public abstract String getScript();

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, getScript());
		UnixUtils.validate(exitValue, "Tomcat script [" + getScript() + "] exit value is [" + exitValue + "]", nonZeroExitValueMode);
	}

	public Mode getNonZeroExitValueMode() {
		return nonZeroExitValueMode;
	}

	public void setNonZeroExitValueMode(Mode nonZeroExitValueMode) {
		this.nonZeroExitValueMode = nonZeroExitValueMode;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
