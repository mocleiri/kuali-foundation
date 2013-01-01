/**
 * Copyright 2011-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.s3;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.aws.s3.S3Utils;

import com.amazonaws.services.s3.AmazonS3Client;

public abstract class AbstractS3Mojo extends AbstractMojo {
	public static final String SEPARATOR = "------------------------------------------------------------------------";

	S3Utils s3Utils = S3Utils.getInstance();

	/**
	 * The Maven project object
	 *
	 * @parameter expression="${project}"
	 * @readonly
	 */
	MavenProject project;

	/**
	 * The AWS Access Key Id for an account on S3
	 *
	 * @parameter expression="${s3.accessKey}"
	 * @required
	 */
	String accessKey;

	/**
	 * The AWS Secret Access Key for an account on S3
	 *
	 * @parameter expression="${s3.secretKey}"
	 * @required
	 */
	String secretKey;

	protected boolean isSkip() {
		return false;
	}

	protected abstract void execute(AmazonS3Client client);

	@Override
	public void execute() throws MojoExecutionException {
		if (isSkip()) {
			getLog().info("Skipping execution");
			return;
		}
		AmazonS3Client client = s3Utils.getClient(accessKey, secretKey);
		execute(client);
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public MavenProject getProject() {
		return project;
	}
}
