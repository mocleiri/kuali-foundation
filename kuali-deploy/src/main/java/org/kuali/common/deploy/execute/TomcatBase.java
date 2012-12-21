package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public abstract class TomcatBase extends SecureBase implements Executable {

	String login = "tomcat";
	String tomcatHome = "/usr/local/tomcat";

	public abstract String getScript();

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, tomcatHome + "/bin/" + getScript());
		UnixUtils.validate(exitValue, "Tomcat script [" + getScript() + "] exit value is [" + exitValue + "]", nonZeroExitValueMode);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTomcatHome() {
		return tomcatHome;
	}

	public void setTomcatHome(String tomcatHome) {
		this.tomcatHome = tomcatHome;
	}

}
