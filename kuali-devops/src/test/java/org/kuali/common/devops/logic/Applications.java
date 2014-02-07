package org.kuali.common.devops.logic;

import static com.google.common.base.Optional.fromNullable;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.Map;
import java.util.Properties;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.devops.model.Scm;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.rice.RiceLoader;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class Applications extends Examiner {

	private static final Logger logger = Loggers.make();

	public static Optional<Application> getApplication(String fqdn) {
		Map<String, String> manifest = Manifests.getManifest(fqdn);
		Optional<Project> optional = Projects.getProject(fqdn, manifest);
		if (optional.isPresent()) {
			Project project = optional.get();
			Properties config = Applications.getConfig(fqdn, project);
			Optional<Database> database = Databases.getDatabase(project.getGroupId(), config);
			Optional<Scm> scm = getScm(project.getProperties());
			Application app = Application.builder().project(project).manifest(manifest).configuration(config).database(database).scm(scm).build();
			return Optional.of(app);
		} else {
			return Optional.absent();
		}
	}

	public static Optional<Scm> getScm(Properties properties) {
		Optional<String> url = fromNullable(properties.getProperty(SCM_URL_KEY));
		Optional<String> revision = fromNullable(properties.getProperty(SCM_REVISION_KEY));
		if (url.isPresent() && revision.isPresent()) {
			return Optional.of(Scm.create(url.get(), revision.get()));
		} else {
			return Optional.absent();
		}
	}

	public static String getConfigFragment(Project project, Properties systemProperties) {
		String groupId = project.getGroupId();
		if (groupId.equals(KualiProjectConstants.STUDENT_GROUP_ID)) {
			return "/home/kuali/main/dev/" + project.getArtifactId() + "-config.xml";
		} else if (groupId.equals(KualiProjectConstants.OLE_GROUP_ID)) {
			String key = "environment";
			String value = systemProperties.getProperty(key);
			checkNotBlank(value, key);
			return "/home/kuali/main/" + value + "/common-config.xml";
		} else {
			return "/home/kuali/main/dev/common-config.xml";
		}
	}

	protected static Properties getConfig(String fqdn, Project project) {
		String fragment = getConfigFragment(fqdn, project);
		String location = PROTOCOL + fqdn + fragment;
		try {
			return RiceLoader.load(location);
		} catch (Exception e) {
			logger.debug("error loading [%s] -> [%s]", location, e.getMessage());
			return new Properties();
		}
	}

	protected static String getConfigFragment(String fqdn, Project project) {
		String groupId = project.getGroupId();
		if (groupId.equals(KualiProjectConstants.STUDENT_GROUP_ID)) {
			return "/home/kuali/main/dev/" + project.getArtifactId() + "-config.xml";
		} else if (groupId.equals(KualiProjectConstants.OLE_GROUP_ID)) {
			Optional<String> environment = getSystemProperty(fqdn, "environment");
			return "/home/kuali/main/" + environment.get() + "/common-config.xml";
		} else {
			return "/home/kuali/main/dev/common-config.xml";
		}
	}

}
