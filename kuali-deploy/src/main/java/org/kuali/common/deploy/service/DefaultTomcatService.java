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
package org.kuali.common.deploy.service;

import org.kuali.common.deploy.context.SecureContext;
import org.kuali.common.deploy.context.TomcatContext;
import org.kuali.common.util.Mode;
import org.kuali.common.util.UnixUtils;

public class DefaultTomcatService implements TomcatService {

	@Override
	public void shutdown(SecureContext security, TomcatContext tomcat) {
		int exitValue = UnixUtils.sshsu(security.getUser(), security.getHostname(), tomcat.getUser(), tomcat.getShutdown());
		UnixUtils.validate(exitValue, "Error shutting down tomcat", Mode.WARN);
	}

	@Override
	public void cleanup(SecureContext security, TomcatContext tomcat) {
		int exitValue = UnixUtils.sshsu(security.getUser(), security.getHostname(), tomcat.getUser(), tomcat.getCleanup());
		UnixUtils.validate(exitValue, "Error cleaning up tomcat");
	}

	@Override
	public void startup(SecureContext security, TomcatContext tomcat) {
		int exitValue = UnixUtils.sshsu(security.getUser(), security.getHostname(), tomcat.getUser(), tomcat.getCleanup());
		UnixUtils.validate(exitValue, "Error starting tomcat");
	}

}
