package org.kuali.common.devops.model;

import org.kuali.common.util.maven.model.Artifact;

import com.google.common.base.Optional;

public enum Artifacts {

	JDK6(HotSpot.GID, HotSpot.JDK6, HotSpot.JDK6_LATEST, HotSpot.LINUX_X64), //
	JDK7(HotSpot.GID, HotSpot.JDK7, HotSpot.JDK7_LATEST, HotSpot.LINUX_X64); //

	private final Artifact artifact;

	private Artifacts(String groupId, String artifactId, String version) {
		this(groupId, artifactId, version, Optional.<String> absent());
	}

	private Artifacts(String groupId, String artifactId, String version, String classifier) {
		this(groupId, artifactId, version, Optional.of(classifier));
	}

	private Artifacts(String groupId, String artifactId, String version, Optional<String> classifier) {
		this.artifact = new Artifact.Builder(groupId, artifactId, version).classifier(classifier.orNull()).build();
	}

	public Artifact getArtifact() {
		return artifact;
	}

	private static final class HotSpot {

		private static final String JDK6_LATEST = "1.6.0-u45";
		private static final String JDK7_LATEST = "1.7.0-u40";

		private static final String GID = "com.oracle";
		private static final String JDK6 = "jdk6";
		private static final String JDK7 = "jdk7";
		private static final String LINUX_X64 = "linux-x64";

	}

}
