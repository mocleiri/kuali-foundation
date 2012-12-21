package org.kuali.common.deploy.execute;

import org.kuali.common.util.Mode;

public abstract class SecureBase {

	Mode nonZeroExitValueMode = Mode.ERROR;
	String user = "root";
	String hostname;

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

}
