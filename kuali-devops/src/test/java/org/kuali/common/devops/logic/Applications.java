package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.rice.RiceLoader;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class Applications extends Examiner {

	private static final Logger logger = Loggers.make();

	public static Optional<Project> getProject(String fqdn) {
		Map<String, String> manifest = Manifests.getManifest(fqdn);
		Properties properties = getProjectProperties(fqdn, manifest);
		if (properties.getProperty("project.artifactId") == null) {
			return Optional.absent();
		} else {
			return Optional.of(ProjectUtils.getProject(properties));
		}
	}

	protected static Properties getConfig(Environment env) {
		if (!env.getApplication().isPresent()) {
			return new Properties();
		}
		String fragment = getConfigFragment(env);
		String location = PROTOCOL + env.getFqdn() + fragment;
		try {
			return RiceLoader.load(location);
		} catch (Exception e) {
			logger.info("error loading [%s] -> [%s]", location, e.getMessage());
			return new Properties();
		}
	}

	protected static String getConfigFragment(Environment env) {
		Project project = env.getApplication().get().getProject();
		String groupId = project.getGroupId();
		if (groupId.equals(KualiProjectConstants.STUDENT_GROUP_ID)) {
			return "/home/kuali/main/dev/" + project.getArtifactId() + "-config.xml";
		} else if (groupId.equals(KualiProjectConstants.OLE_GROUP_ID)) {
			Optional<String> environment = getSystemProperty(env.getFqdn(), "environment");
			checkState(environment.isPresent(), "could not locate system property -> [%s]", environment);
			return "/home/kuali/main/" + environment + "/common-config.xml";
		} else {
			return "/home/kuali/main/dev/common-config.xml";
		}
	}

	public static Properties getProjectProperties(String fqdn, Map<String, String> manifest) {
		Optional<String> bundleSymbolicName = Optional.fromNullable(manifest.get("Bundle-SymbolicName"));
		if (!bundleSymbolicName.isPresent()) {
			return new Properties();
		} else {
			String location = getProjectPropertiesPath(fqdn, bundleSymbolicName.get());
			Properties properties = PropertyUtils.loadOrCreateSilently(location);

			// Only way to get reliable SVN information is from the manifest
			Optional<String> url = getScmUrl(manifest);
			Optional<String> revision = Optional.of(manifest.get("SVN-Revision"));
			if (revision.isPresent() && url.isPresent()) {
				properties.setProperty("project.scm.url", url.get());
				properties.setProperty("project.scm.revision", revision.get());
			} else {
				properties.remove("project.scm.url");
				properties.remove("project.scm.revision");
			}
			return properties;
		}
	}

	protected static String getProjectPropertiesPath(String fqdn, String bundleSymbolicName) {
		String fragment = "/tomcat/webapps/ROOT/WEB-INF/classes/META-INF";
		String groupId = getGroupId(bundleSymbolicName);
		String artifactId = getArtifactId(bundleSymbolicName);
		String filename = "project.properties";
		StringBuilder sb = new StringBuilder();
		sb.append(PROTOCOL);
		sb.append(fqdn);
		sb.append(fragment);
		sb.append("/");
		sb.append(Str.getPath(groupId));
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		sb.append(filename);
		return sb.toString();
	}

	protected static String getGroupId(String bundleSymbolicName) {
		char separator = '.';
		List<String> tokens = Splitter.on(separator).splitToList(bundleSymbolicName);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tokens.size() - 1; i++) {
			if (i != 0) {
				sb.append(separator);
			}
			String token = tokens.get(i);
			sb.append(token);
			// Shorten "org.kuali.student.*" -> "org.kuali.student"
			if (token.equals("student")) {
				break;
			}
		}
		return sb.toString();
	}

	protected static String getArtifactId(String bundleSymbolicName) {
		List<String> tokens = Splitter.on('.').splitToList(bundleSymbolicName);
		return tokens.get(tokens.size() - 1);
	}

	protected static Optional<String> getScmUrl(Map<String, String> manifest) {
		String url = manifest.get("SVN-URL");
		if (url == null) {
			return Optional.absent();
		}
		if (url.indexOf("${") != -1) {
			return Optional.absent();
		}
		if (!LocationUtils.exists(url)) {
			return Optional.absent();
		}
		return Optional.of("scm:svn:" + url);
	}

}
