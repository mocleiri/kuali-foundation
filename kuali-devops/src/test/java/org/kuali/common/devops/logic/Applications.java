package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkState;

import java.util.Properties;

import org.kuali.common.devops.model.Environment;
import org.kuali.common.util.log.Loggers;
import org.kuali.common.util.project.KualiProjectConstants;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.properties.rice.RiceLoader;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class Applications extends Examiner {

	private static final Logger logger = Loggers.make();

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

}
