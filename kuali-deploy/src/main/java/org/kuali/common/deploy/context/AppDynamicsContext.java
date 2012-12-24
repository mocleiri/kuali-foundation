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
package org.kuali.common.deploy.context;

import java.io.File;

public class AppDynamicsContext {

	String remoteControllerDir;
	String remoteControllerFile;
	File localControllerFile;

	public String getRemoteControllerDir() {
		return remoteControllerDir;
	}

	public void setRemoteControllerDir(String remoteControllerDir) {
		this.remoteControllerDir = remoteControllerDir;
	}

	public String getRemoteControllerFile() {
		return remoteControllerFile;
	}

	public void setRemoteControllerFile(String remoteControllerFile) {
		this.remoteControllerFile = remoteControllerFile;
	}

	public File getLocalControllerFile() {
		return localControllerFile;
	}

	public void setLocalControllerFile(File localControllerFile) {
		this.localControllerFile = localControllerFile;
	}

}
