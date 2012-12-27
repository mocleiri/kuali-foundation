package org.kuali.common.util.secure;

import java.util.Properties;

import com.jcraft.jsch.JSch;

public class JSchContext {

	JSch jsch;
	String username;
	String hostname;
	int port;
	Properties options;
	int timeout;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Properties getOptions() {
		return options;
	}

	public void setOptions(Properties options) {
		this.options = options;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public JSch getJsch() {
		return jsch;
	}

	public void setJsch(JSch jsch) {
		this.jsch = jsch;
	}

}
