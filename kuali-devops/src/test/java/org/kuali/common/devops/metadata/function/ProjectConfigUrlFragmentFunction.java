package org.kuali.common.devops.metadata.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.project.KualiProjectConstants.OLE_GROUP_ID;
import static org.kuali.common.util.project.KualiProjectConstants.STUDENT_GROUP_ID;

import java.util.Properties;

import org.kuali.common.devops.metadata.model.RemoteEnvironment;
import org.kuali.common.util.project.model.Project;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public final class ProjectConfigUrlFragmentFunction implements Function<Project, Optional<String>> {

	public ProjectConfigUrlFragmentFunction(Optional<RemoteEnvironment> remoteEnvironment) {
		this(remoteEnvironment, Optional.<String> absent());
	}

	public ProjectConfigUrlFragmentFunction(Optional<RemoteEnvironment> remoteEnvironment, Optional<String> prefix) {
		this.remoteEnvironment = checkNotNull(remoteEnvironment, "remoteEnvironment");
		this.prefix = prefix;
	}

	private final Optional<RemoteEnvironment> remoteEnvironment;
	private final Optional<String> prefix;

	@Override
	public Optional<String> apply(Project project) {
		checkNotNull(project, "project");
		return getConfigFragment(project, remoteEnvironment);
	}

	protected Optional<String> getConfigFragment(Project project, Optional<RemoteEnvironment> remoteEnvironment) {
		String groupId = project.getGroupId();
		if (groupId.equals(STUDENT_GROUP_ID)) {
			// KS specific logic
			if (prefix.isPresent()) {
				return Optional.of("/" + prefix.get() + "/home/kuali/main/dev/" + project.getArtifactId() + "-config.xml");
			} else {
				return Optional.of("/home/kuali/main/dev/" + project.getArtifactId() + "-config.xml");
			}
		} else if (groupId.equals(OLE_GROUP_ID)) {
			// OLE specific logic
			return getOleConfigFragment(remoteEnvironment, prefix);
		} else {
			// Everyone else
			if (prefix.isPresent()) {
				return Optional.of("/" + prefix.get() + "/home/kuali/main/dev/common-config.xml");
			} else {
				return Optional.of("/home/kuali/main/dev/common-config.xml");
			}
		}
	}

	protected Optional<String> getOleConfigFragment(Optional<RemoteEnvironment> remoteEnvironment, Optional<String> prefix) {
		// OLE specific logic
		if (!remoteEnvironment.isPresent()) {
			return absent();
		}
		Properties systemProperties = remoteEnvironment.get().getSystem();
		Optional<String> value = fromNullable(systemProperties.getProperty("environment"));
		if (!value.isPresent() || isBlank(value.get().trim())) {
			return absent();
		} else {
			if (prefix.isPresent()) {
				return Optional.of("/" + prefix.get() + "/home/kuali/main/" + value.get() + "/common-config.xml");
			} else {
				return Optional.of("/home/kuali/main/" + value.get() + "/common-config.xml");
			}
		}
	}

	public Optional<RemoteEnvironment> getRemoteEnvironment() {
		return remoteEnvironment;
	}

	public Optional<String> getPrefix() {
		return prefix;
	}

}
