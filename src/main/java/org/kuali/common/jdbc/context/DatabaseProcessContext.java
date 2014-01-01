/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc.context;

public class DatabaseProcessContext {

	String vendor;
	String driver;
	String url;
	String username;
	String password;
	String dbaUrl;
	String dbaUsername;
	String dbaPassword;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbaUrl() {
		return dbaUrl;
	}

	public void setDbaUrl(String dbaUrl) {
		this.dbaUrl = dbaUrl;
	}

	public String getDbaUsername() {
		return dbaUsername;
	}

	public void setDbaUsername(String dbaUsername) {
		this.dbaUsername = dbaUsername;
	}

	public String getDbaPassword() {
		return dbaPassword;
	}

	public void setDbaPassword(String dbaPassword) {
		this.dbaPassword = dbaPassword;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
