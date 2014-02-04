package org.kuali.common.devops.logic;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class Projects extends Examiner {

	public static Optional<Project> getProject(String fqdn, Map<String, String> manifest) {
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

			// Most reliable way to get SVN information is from the manifest
			Optional<String> url = getScmUrl(manifest, properties);
			String revision = getScmRevision(manifest);

			if (url.isPresent()) {
				// Override whatever is in the project properties with what we found
				properties.setProperty(SCM_URL_KEY, url.get());
				properties.setProperty(SCM_REVISION_KEY, revision);
			} else {
				// Remove these 2 properties so nobody can even attempt to display inaccurate SCM info
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

	/**
	 * Returns Optional.absent() unless we can locate a live URL that we can actually contact
	 */
	protected static Optional<String> getScmUrl(Map<String, String> manifest, Properties properties) {
		// Most reliable method for getting the url is via MANIFEST.MF
		Optional<String> url = getScmUrlFromManifest(manifest);
		if (url.isPresent()) {
			// If we found one, we are done
			return url;
		} else {
			// If MANIFEST.MF fails, try to get it from project.properties
			// The problem here, is that Maven assumes artifactId == directory name for sub-modules
			// If that isn't the case, the URL from project.properties points to a non-existent location
			return getScmUrlFromProperties(properties);
		}
	}

	protected static Optional<String> getScmUrlFromManifest(Map<String, String> manifest) {
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

	protected static Optional<String> getScmUrlFromProperties(Properties properties) {
		String url = properties.getProperty("project.scm.url");
		if (url == null) {
			return Optional.absent();
		}
		List<String> tokens = Lists.newArrayList(Splitter.on(':').splitToList(url));
		tokens.remove(0); // scm
		tokens.remove(0); // svn
		String newUrl = Joiner.on(':').join(tokens);

		// Make sure the location actually exists
		if (LocationUtils.exists(newUrl)) {
			return Optional.of(newUrl);
		} else {
			return Optional.absent();
		}
	}

	protected static String getScmRevision(Map<String, String> manifest) {
		String revision = StringUtils.trimToNull(manifest.get("SVN-Revision"));
		if (revision == null || revision.indexOf("${") != -1) {
			return "n/a";
		} else {
			return revision;
		}
	}

}
