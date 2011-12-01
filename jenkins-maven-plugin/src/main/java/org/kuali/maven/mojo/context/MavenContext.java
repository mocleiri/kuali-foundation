package org.kuali.maven.mojo.context;

import org.apache.maven.project.MavenProject;

public class MavenContext {
	MavenProject project;
	String scmType;
	String scmUrl;
	String majorVersion;

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject mavenProject) {
		this.project = mavenProject;
	}

	public String getScmType() {
		return scmType;
	}

	public void setScmType(String scmType) {
		this.scmType = scmType;
	}

	public String getScmUrl() {
		return scmUrl;
	}

	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

}
