/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.ec2.model.security;

import org.kuali.common.util.Ports;

import com.google.common.collect.ImmutableList;

public enum NamedPermissions {

	ALLOW_SSH_FROM_ANYWHERE(Ports.SSH), // Allow SSH (port 22) from anywhere
	ALLOW_HTTP_FROM_ANYWHERE(Ports.HTTP), // Allow HTTP (port 80) from anywhere
	ALLOW_HTTPS_FROM_ANYWHERE(Ports.HTTPS); // Allow HTTPS (port 443) from anywhere

	private final Permission permission;

	private NamedPermissions(int port) {
		this.permission = Permission.create(port);
	}

	public Permission getPermission() {
		return permission;
	}

	public static final ImmutableList<Permission> APPLICATION_SERVER_PERMISSIONS = ImmutableList.of(ALLOW_SSH_FROM_ANYWHERE.getPermission(), ALLOW_HTTP_FROM_ANYWHERE.getPermission(), ALLOW_HTTPS_FROM_ANYWHERE.getPermission());

}
