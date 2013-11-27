package org.kuali.common.devops.model;

import org.kuali.common.util.maven.model.Artifact;

public enum Zips {

	JDK6(Artifacts.JDK6.getArtifact()), //
	JDK7(Artifacts.JDK7.getArtifact()); //

	private final ZipPackage pkg;

	private Zips(Artifact artifact) {
		this.pkg = new ZipPackage.Builder(artifact).build();
	}

	public ZipPackage getPackage() {
		return pkg;
	}

}
