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

	private static final String METAINF_FRAGMENT = "/tomcat/webapps/ROOT/WEB-INF/classes/META-INF";
	private static final String FILENAME = "project.properties";
	private static final String BUNDLE_SYMBOLIC_NAME_KEY = "Bundle-SymbolicName";

	public static Optional<Project> getProject(String fqdn, Properties manifest) {
		Properties properties = getProjectProperties(fqdn, manifest);
		if (properties.getProperty("project.artifactId") == null) {
			return Optional.absent();
		} else {
			return Optional.of(ProjectUtils.getProject(properties));
		}
	}

	public static Properties getProjectProperties(String fqdn, Properties manifest) {
		Optional<String> bundleSymbolicName = Optional.fromNullable(manifest.getProperty(BUNDLE_SYMBOLIC_NAME_KEY));
		if (!bundleSymbolicName.isPresent()) {
			return new Properties();
		} else {
			String location = getProjectPropertiesUrl(fqdn, bundleSymbolicName.get());
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

	public static Optional<String> getProjectPropertiesUrlFragment(Map<String, String> manifest) {
		Optional<String> name = Optional.fromNullable(manifest.get(BUNDLE_SYMBOLIC_NAME_KEY));
		if (name.isPresent()) {
			return Optional.of(getProjectPropertiesUrlFragment(name.get()));
		} else {
			return Optional.absent();
		}
	}

	private static String getProjectPropertiesUrlFragment(String bundleSymbolicName) {
		String groupId = getGroupId(bundleSymbolicName);
		String artifactId = getArtifactId(bundleSymbolicName);
		StringBuilder sb = new StringBuilder();
		sb.append(METAINF_FRAGMENT);
		sb.append("/");
		sb.append(Str.getPath(groupId));
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		sb.append(FILENAME);
		return sb.toString();
	}

	private static String getProjectPropertiesUrl(String fqdn, String bundleSymbolicName) {
		String groupId = getGroupId(bundleSymbolicName);
		String artifactId = getArtifactId(bundleSymbolicName);
		StringBuilder sb = new StringBuilder();
		sb.append(PROTOCOL);
		sb.append(fqdn);
		sb.append(METAINF_FRAGMENT);
		sb.append("/");
		sb.append(Str.getPath(groupId));
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		sb.append(FILENAME);
		return sb.toString();
	}

	private static String getGroupId(String bundleSymbolicName) {
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

	private static String getArtifactId(String bundleSymbolicName) {
		List<String> tokens = Splitter.on('.').splitToList(bundleSymbolicName);
		return tokens.get(tokens.size() - 1);
	}

	/**
	 * Returns Optional.absent() unless we can locate a live URL that we can actually contact
	 */
	private static Optional<String> getScmUrl(Properties manifest, Properties properties) {
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

	private static Optional<String> getScmUrlFromManifest(Properties manifest) {
		String url = manifest.getProperty("SVN-URL");
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

	private static Optional<String> getScmUrlFromProperties(Properties properties) {
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

	private static String getScmRevision(Properties manifest) {
		String revision = StringUtils.trimToNull(manifest.getProperty("SVN-Revision"));
		if (revision == null || revision.indexOf("${") != -1) {
			return "n/a";
		} else {
			return revision;
		}
	}

}
