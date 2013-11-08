package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.util.maven.model.Artifact;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

public class ArtifactUtils {

	private static final String JDK7_GROUP_ID_KEY = "jdk7.groupId";
	private static final String JDK7_ARTIFACT_ID_KEY = "jdk7.artifactId";
	private static final String JDK7_VERSION_KEY = "jdk7.version";

	private static final String JDK6_GROUP_ID_KEY = "jdk6.groupId";
	private static final String JDK6_ARTIFACT_ID_KEY = "jdk6.artifactId";
	private static final String JDK6_VERSION_KEY = "jdk6.version";

	private static final String JDK_CLASSIFIER = "linux-x64";
	private static final String ZIP = "zip";
	private static final String JDK_GROUP_ID = "com.oracle";

	private static final String JDK6_ARTIFACT_ID = "jdk6";
	private static final String JDK7_ARTIFACT_ID = "jdk7";

	private static final Artifact JDK6NOVERSION = new Artifact.Builder(JDK_GROUP_ID, JDK6_ARTIFACT_ID, NullUtils.NONE).classifier(JDK_CLASSIFIER).type(ZIP).build();
	private static final Artifact JDK7NOVERSION = new Artifact.Builder(JDK_GROUP_ID, JDK7_ARTIFACT_ID, NullUtils.NONE).classifier(JDK_CLASSIFIER).type(ZIP).build();

	public static Artifact getTomcat(String version) {
		return new Artifact.Builder("org.apache.tomcat", "apache-tomcat", version).type(ZIP).build();
	}

	public static Artifact getJDK7(String version) {
		return new Artifact.Builder(JDK_GROUP_ID, JDK7_ARTIFACT_ID, version).classifier(JDK_CLASSIFIER).type(ZIP).build();
	}

	public static Artifact getJDK6(String version) {
		return new Artifact.Builder(JDK_GROUP_ID, JDK6_ARTIFACT_ID, version).classifier(JDK_CLASSIFIER).type(ZIP).build();
	}

	public static Artifact getJDK7(EnvironmentService env) {
		return getJDK7(env, JDK7NOVERSION);
	}

	public static Artifact getJDK7(EnvironmentService env, Artifact provided) {
		String groupId = env.getString(JDK7_GROUP_ID_KEY, provided.getGroupId());
		String artifactId = env.getString(JDK7_ARTIFACT_ID_KEY, provided.getArtifactId());
		String version = NullUtils.trimToNull(env.getString(JDK7_VERSION_KEY, provided.getVersion()));
		return new Artifact.Builder(groupId, artifactId, version).classifier(JDK_CLASSIFIER).type(ZIP).build();
	}

	public static Artifact getJDK6(EnvironmentService env) {
		return getJDK6(env, JDK6NOVERSION);
	}

	public static Artifact getJDK6(EnvironmentService env, Artifact provided) {
		String groupId = env.getString(JDK6_GROUP_ID_KEY, provided.getGroupId());
		String artifactId = env.getString(JDK6_ARTIFACT_ID_KEY, provided.getArtifactId());
		String version = NullUtils.trimToNull(env.getString(JDK6_VERSION_KEY, provided.getVersion()));
		return new Artifact.Builder(groupId, artifactId, version).classifier(JDK_CLASSIFIER).type(ZIP).build();
	}

}
