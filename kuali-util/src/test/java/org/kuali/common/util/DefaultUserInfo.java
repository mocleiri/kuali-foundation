package org.kuali.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.UserInfo;

public class DefaultUserInfo implements UserInfo {
	private static final Logger logger = LoggerFactory.getLogger(DefaultUserInfo.class);

	String passphrase;
	String password;

	@Override
	public boolean promptPassword(String message) {
		return false;
	}

	@Override
	public boolean promptPassphrase(String message) {
		return false;
	}

	@Override
	public boolean promptYesNo(String message) {
		return false;
	}

	@Override
	public void showMessage(String message) {
		logger.info(message);
	}

	@Override
	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
