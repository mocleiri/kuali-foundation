package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.project.model.ImmutableProject;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class Projects extends Examiner {

	private static final String BUNDLE_SYMBOLIC_NAME_KEY = "Bundle-SymbolicName";

	public static Project getProjectWithAccurateSCMInfo(Project project, Properties manifest) {
		Optional<String> bundleSymbolicName = fromNullable(manifest.getProperty(BUNDLE_SYMBOLIC_NAME_KEY));
		if (!bundleSymbolicName.isPresent()) {
			return ImmutableProject.copyOf(project);
		} else {
			Properties newProps = new Properties();
			newProps.putAll(project.getProperties());

			// Most reliable way to get SVN information is from the manifest but this method attempts to get it from both
			// MANIFEST.MF and project.properties. It also verifies that it can actually contact the location.
			Optional<String> url = getScmUrl(manifest, newProps);
			String revision = getScmRevision(manifest);

			if (url.isPresent()) {
				// Override whatever is in the project properties with what we found
				newProps.setProperty(SCM_URL_KEY, url.get());
				newProps.setProperty(SCM_REVISION_KEY, revision);
			} else {
				// Remove these 2 properties so nobody can even attempt to display inaccurate SCM info
				newProps.remove(SCM_URL_KEY);
				newProps.remove(SCM_REVISION_KEY);
			}
			return new ImmutableProject(project.getGroupId(), project.getArtifactId(), project.getVersion(), newProps);
		}
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
			return absent();
		}
		if (url.indexOf("${") != -1) {
			return absent();
		}
		if (!LocationUtils.exists(url)) {
			return absent();
		}
		return Optional.of(url);
	}

	private static Optional<String> getScmUrlFromProperties(Properties properties) {
		String url = properties.getProperty("project.scm.url");
		if (url == null) {
			return Optional.absent();
		}
		List<String> tokens = newArrayList(Splitter.on(':').splitToList(url));
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
