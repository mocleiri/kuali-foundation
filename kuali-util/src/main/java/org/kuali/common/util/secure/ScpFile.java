/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.secure;

import java.io.File;

import org.kuali.common.util.LocationUtils;

public class ScpFile {

	String username;
	String hostname;
	String filename;

	public ScpFile() {
		this(null, null, null);
	}

	public ScpFile(File file) {
		this(null, null, LocationUtils.getCanonicalPath(file));
	}

	public ScpFile(String filename) {
		this(null, null, filename);
	}

	public ScpFile(String username, String hostname, String filename) {
		super();
		this.username = username;
		this.hostname = hostname;
		this.filename = filename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
