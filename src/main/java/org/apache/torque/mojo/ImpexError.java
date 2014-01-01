/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.torque.mojo;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class ImpexError {
	Throwable throwable;
	String message;
	Properties info;
	String url;
	String driver;
	boolean showPassword;

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Properties getInfo() {
		return info;
	}

	public void setInfo(Properties info) {
		this.info = info;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n\n");
		if (!StringUtils.isEmpty(message)) {
			sb.append(message + "\n\n");
		}
		if (getThrowable() != null) {
			sb.append("------------------------------------------------------\n");
			String emsg = getThrowable().getMessage();
			sb.append(emsg);
			if (!emsg.endsWith("\n")) {
				sb.append("\n");
			}
		}
		sb.append(toString(getInfo()));
		return sb.toString();
	}

	public String toString(Properties info) {
		StringBuffer sb = new StringBuffer();
		sb.append("------------------------------------------------------\n\n");
		sb.append("The following information was provided to JDBC:\n");
		sb.append("------------------------------------------------------\n");
		sb.append("URL: " + getUrl() + "\n");
		sb.append("Driver: " + getDriver() + "\n");
		String username = info.getProperty(AbstractSQLExecutorMojo.DRIVER_INFO_PROPERTIES_USER);
		if (StringUtils.isEmpty(username)) {
			sb.append("Username: [No username was supplied]\n");
		} else {
			sb.append("Username: " + username + "\n");
		}
		String password = info.getProperty(AbstractSQLExecutorMojo.DRIVER_INFO_PROPERTIES_PASSWORD);
		if (isShowPassword()) {
			sb.append("Password: " + password + "\n");
		} else {
			if (StringUtils.isEmpty(password)) {
				sb.append("Password: [No password was supplied]\n");
			} else {
				sb.append("Password: *******\n");
			}
		}
		sb.append("------------------------------------------------------\n");
		sb.append("\n");
		return sb.toString();

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public boolean isShowPassword() {
		return showPassword;
	}

	public void setShowPassword(boolean showPassword) {
		this.showPassword = showPassword;
	}

}
