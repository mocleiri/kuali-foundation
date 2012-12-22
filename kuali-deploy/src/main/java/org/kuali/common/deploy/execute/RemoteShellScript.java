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
package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class RemoteShellScript extends SecureBase implements Executable {

	String login;
	String script;

	public RemoteShellScript() {
		this(null, null, null);
	}

	public RemoteShellScript(String hostname, String login, String script) {
		super();
		this.login = login;
		this.script = script;
		this.hostname = hostname;
	}

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshsu(user, hostname, login, script);
		UnixUtils.validate(exitValue, "Error executing remote shell script", nonZeroExitValueMode);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
