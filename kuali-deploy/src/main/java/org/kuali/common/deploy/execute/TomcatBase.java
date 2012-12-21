package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public abstract class TomcatBase extends SecureBase implements Executable {

	String login = "tomcat";

	public abstract String getScript();

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, getScript());
		UnixUtils.validate(exitValue, "Tomcat script [" + getScript() + "] exit value is [" + exitValue + "]", nonZeroExitValueMode);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
