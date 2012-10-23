package org.kuali.maven.plugins.s3;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.aws.s3.S3Utils;

import com.amazonaws.services.s3.AmazonS3Client;

public abstract class AbstractS3Mojo extends AbstractMojo {
	S3Utils s3Utils = S3Utils.getInstance();

	/**
	 * The Maven project object
	 *
	 * @parameter expression="${project}"
	 * @readonly
	 */
	MavenProject project;

	/**
	 * The AWS Access Key Id for an account on EC2
	 *
	 * @parameter expression="${s3.accessKey}"
	 * @required
	 */
	String accessKey;

	/**
	 * The AWS Secret Access Key for an account on EC2
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
