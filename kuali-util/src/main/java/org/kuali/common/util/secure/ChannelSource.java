package org.kuali.common.util.secure;

import com.jcraft.jsch.JSch;

public class ChannelSource {

	JSch jsch;
	SessionContext context;

	public JSch getJsch() {
		return jsch;
	}

	public void setJsch(JSch jsch) {
		this.jsch = jsch;
	}

	public SessionContext getContext() {
		return context;
	}

	public void setContext(SessionContext context) {
		this.context = context;
	}

}
