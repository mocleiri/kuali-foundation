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


public class ScpFile {

	String username;
	String hostname;
	String filename;

	public ScpFile() {
		this(null, null, null);
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

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((filename == null) ? 0 : filename.hashCode());
	    result = prime * result + ((hostname == null) ? 0 : hostname.hashCode());
	    result = prime * result + ((username == null) ? 0 : username.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    ScpFile other = (ScpFile) obj;
	    if (filename == null) {
		    if (other.filename != null)
			    return false;
	    } else if (!filename.equals(other.filename))
		    return false;
	    if (hostname == null) {
		    if (other.hostname != null)
			    return false;
	    } else if (!hostname.equals(other.hostname))
		    return false;
	    if (username == null) {
		    if (other.username != null)
			    return false;
	    } else if (!username.equals(other.username))
		    return false;
	    return true;
    }

}
