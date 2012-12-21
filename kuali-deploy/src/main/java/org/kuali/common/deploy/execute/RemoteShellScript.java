package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class RemoteShellScript extends SecureBase implements Executable {

	String login;
	String script;

	public RemoteShellScript() {
		this(null, null, null);
	}

	public RemoteShellScript(String hostname, String login, String script) {
		super();
		this.login = login;
		this.script = script;
		this.hostname = hostname;
	}

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, script);
		UnixUtils.validate(exitValue, "Error executing remote shell script", nonZeroExitValueMode);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
