package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public abstract class TomcatBase extends SecureBase implements Executable {

	String login = Constants.TOMCAT_USER;
	String tomcatHome = Constants.TOMCAT_HOME;
	String tomcatLogs = Constants.TOMCAT_LOGS;
	String tomcatSetEnv = Constants.TOMCAT_SETENV;
	String tomcatBin = Constants.TOMCAT_BIN;
	String tomcatConf = Constants.TOMCAT_CONF;
	String tomcatWebapps = Constants.TOMCAT_WEBAPPS;

	public abstract String getScript();

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, tomcatBin + "/" + getScript());
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
