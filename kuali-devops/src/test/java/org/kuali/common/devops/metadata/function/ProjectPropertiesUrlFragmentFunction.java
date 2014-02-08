package org.kuali.common.devops.metadata.function;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Str;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public final class ProjectPropertiesUrlFragmentFunction implements Function<Properties, Optional<String>> {

	private static final String FILENAME = "project.properties";
	private static final String BUNDLE_SYMBOLIC_NAME_KEY = "Bundle-SymbolicName";
	private static final String METAINF_FRAGMENT = "/tomcat/webapps/ROOT/WEB-INF/classes/META-INF";

	@Override
	public Optional<String> apply(Properties manifest) {
		checkNotNull(manifest, "manifest");
		return getProjectPropertiesUrlFragment(manifest);
	}

	protected Optional<String> getProjectPropertiesUrlFragment(Properties manifest) {
		Optional<String> name = Optional.fromNullable(manifest.getProperty(BUNDLE_SYMBOLIC_NAME_KEY));
		if (name.isPresent()) {
			return Optional.of(getProjectPropertiesUrlFragment(name.get()));
		} else {
			return Optional.absent();
		}
	}

	protected String getProjectPropertiesUrlFragment(String bundleSymbolicName) {
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

	protected String getGroupId(String bundleSymbolicName) {
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

	protected String getArtifactId(String bundleSymbolicName) {
		List<String> tokens = Splitter.on('.').splitToList(bundleSymbolicName);
		return tokens.get(tokens.size() - 1);
	}

}
