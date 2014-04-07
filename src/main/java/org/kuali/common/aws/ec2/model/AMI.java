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
package org.kuali.common.aws.ec2.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

public enum AMI {

	// This is a bare bones Amazon Linux box with virtually nothing except yum installed on it
	// amzn-ami-minimal-pv-2013.09.0.x86_64-ebs
	AMAZON_LINUX_64_BIT_MINIMAL_AMI_2013_09(Distro.AMAZON, "2013.09", "ami-65792c0c", "amzn-ami-minimal-pv-2013.09.0.x86_64-ebs"), //

	// Ubuntu creates AMI's specific to a region

	// This one is for US East 1
	UBUNTU_64_BIT_PRECISE_LTS_1204_US_EAST_1(Distro.UBUNTU, "12.04", "ami-0b9c9f62", "ubuntu-precise-12.04-amd64-server-20140227-ebs (us-east-1)"), //

	// Started using this one in US West 1 when the Ubuntu bucket in US East 1 started having lame a** issues.
	// apt-get install tomcat7 resulted in a failure with the message "HTTP 403 Forbidden" using the US East 1 bucket from which Ubuntu's package manager downloads binaries
	// apt-get install tomcat7 using the US West 1 bucket worked like a champ
	// This occurred randomly late on a Friday afternoon (March 14, 2014)
	UBUNTU_64_BIT_PRECISE_LTS_1204_US_WEST_1(Distro.UBUNTU, "12.04", "ami-709ba735", "ubuntu-precise-12.04-amd64-server-20140227-ebs (us-west-1)"), //
	UBUNTU_64_BIT_PRECISE_LTS_1204_US_WEST_2(Distro.UBUNTU, "12.04", "ami-c8bed2f8", "ubuntu-precise-12.04-amd64-server-20140227-ebs (us-west-2)");

	private final Distro distro;
	private final String version;
	private final String id;
	private final String description;

	private AMI(Distro distro, String version, String id, String description) {
		this.distro = checkNotNull(distro, "distro");
		this.version = checkNotBlank(version, "version");
		this.id = checkNotBlank(id, "id");
		this.description = checkNotBlank(description, "description");
	}

	public Distro getDistro() {
		return distro;
	}

	public String getVersion() {
		return version;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}
