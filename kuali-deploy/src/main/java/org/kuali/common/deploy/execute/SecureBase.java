/**
 * Copyright 2004-2013 The Kuali Foundation
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
