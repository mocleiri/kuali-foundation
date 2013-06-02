package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.DefaultProjectContext;

public class DeployProjectContext extends DefaultProjectContext {

	private static final String ARTIFACT_ID = "kuali-deploy";
	private static final List<String> LOCATIONS = getLocations();

	public DeployProjectContext() {
		super(ARTIFACT_ID, new ArrayList<String>(LOCATIONS));
	}

	private static final List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/common/deploy/deploy.properties");
		locations.add("classpath:org/kuali/common/deploy/driver.properties");
		locations.add("classpath:${project.groupId.path}/deploy.properties");
		locations.add("classpath:${project.groupId.path}/${project.artifactId}.properties");
		locations.add("classpath:${project.groupId.path}/env${deploy.env}.properties");
		return locations;
	}

}
