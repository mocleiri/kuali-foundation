package org.kuali.common.devops.logic;

import java.util.Properties;

import org.kuali.common.devops.model.Application;
import org.kuali.common.devops.model.Database;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.rice.RiceLoader;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class Applications extends Examiner {

	private static final Logger logger = Loggers.make();

	public static Optional<Application> getApplication(String fqdn) {
		Optional<Project> project = Projects.getProject(fqdn);
		if (project.isPresent()) {
			Properties config = Applications.getConfig(fqdn, project.get());
			Database database = Databases.getDatabase(project.get().getGroupId(), config);
			return Optional.of(Application.create(project.get(), config, database));
		} else {
			return Optional.absent();
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
