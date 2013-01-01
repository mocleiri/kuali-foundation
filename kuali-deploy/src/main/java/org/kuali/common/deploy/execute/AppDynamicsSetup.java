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

import java.io.File;

import org.kuali.common.util.UnixUtils;

public class AppDynamicsSetup extends CopyToRemote {

	public AppDynamicsSetup() {
		this(null, null);
	}

	public AppDynamicsSetup(String hostname, File controller) {
		super();
		this.hostname = hostname;
		this.localFile = controller;
		this.remoteFile = Constants.APP_DYNAMICS_CONTROLLER;
		this.owner = Constants.TOMCAT_USER;
		this.group = Constants.TOMCAT_GROUP;
	}

	@Override
	public void execute() {
		int mkdir = UnixUtils.sshmkdir(user, hostname, Constants.APP_DYNAMICS_DIR);
		UnixUtils.validate(mkdir, "Error creating AppDynamics directory", nonZeroExitValueMode);
		super.execute();
	}

}
