/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.db;

/**
 * A pojo containing JDBC related configuration information. eg JDBC drivers, url fragments and type
 */
public class JDBCConfiguration {
	public static final JDBCConfiguration UNKNOWN_CONFIG = new JDBCConfiguration(DatabaseType.UNKNOWN);

	public JDBCConfiguration() {
		this(null);
	}

	public JDBCConfiguration(DatabaseType type) {
		super();
		this.type = type;
	}

	DatabaseType type;
	String urlFragment;
	String driver;
	public DatabaseType getType() {
		return type;
	}

	public void setType(DatabaseType type) {
		this.type = type;
	}

	public String getUrlFragment() {
		return urlFragment;
	}

	public void setUrlFragment(String urlFragment) {
		this.urlFragment = urlFragment;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}
