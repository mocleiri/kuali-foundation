package org.kuali.common.devops.logic;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class Projects extends Examiner {

	public static Optional<Project> getProject(String fqdn) {
		Map<String, String> manifest = Manifests.getManifest(fqdn);
		Properties properties = getProjectProperties(fqdn, manifest);
		if (properties.getProperty("project.artifactId") == null) {
			return Optional.absent();
		} else {
			return Optional.of(ProjectUtils.getProject(properties));
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
				properties.setProperty(SCM_URL_KEY, url.get());
				properties.setProperty(SCM_REVISION_KEY, revision.get());
			} else {
				properties.remove(SCM_URL_KEY);
				properties.remove(SCM_REVISION_KEY);
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
		return Optional.of(url);
	}

}
