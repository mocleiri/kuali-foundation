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
package org.kuali.common.deploy;

import java.io.File;

import org.kuali.common.deploy.context.DeployContext;
import org.kuali.common.util.UnixUtils;

public class DeployUtil {

	public static final void scpTomcatFile(DeployContext context, File localFile, String remoteFile) {
		String user = context.getSecurity().getUser();
		String hostname = context.getSecurity().getHostname();
		String owner = context.getTomcat().getUser();
		String group = context.getTomcat().getGroup();

		String destination = UnixUtils.getLocation(user, hostname, remoteFile);
		int scp = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(scp, "Error completing secure copy");
		int chown = UnixUtils.sshchown(user, hostname, owner, group, remoteFile);
		UnixUtils.validate(chown, "Error changing file ownership");
	}

}
