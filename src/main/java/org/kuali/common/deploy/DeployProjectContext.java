package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.deploy.config.DeployProjectConstants;
import org.kuali.common.util.Str;

/**
 * @deprecated
 */
@Deprecated
public class DeployProjectContext extends org.kuali.common.util.DefaultProjectContext {

	private static final String ARTIFACT_ID = DeployProjectConstants.ARTIFACT_ID;
	private static final List<String> LOCATIONS = getLocations();

	public DeployProjectContext() {
		super(ARTIFACT_ID, new ArrayList<String>(LOCATIONS));
	}

	private static final List<String> getLocations() {
		List<String> locations = new ArrayList<String>();

		// Added for backwards compatibility reasons only
		locations.add("classpath:org/kuali/common/kuali-impex-producer/sql/schema.properties");
		locations.add("classpath:org/kuali/common/kuali-impex-producer/sql/mpx.properties");
		// Added for backwards compatibility reasons only

		locations.add("classpath:" + Str.getPath(org.kuali.common.util.MavenConstants.KUALI_COMMON_GROUP_ID) + "/deploy/deploy.properties");
		locations.add("classpath:" + Str.getPath(org.kuali.common.util.MavenConstants.KUALI_COMMON_GROUP_ID) + "/deploy/driver.properties");
		locations.add("classpath:${project.groupId.path}/deploy.properties");
		locations.add("classpath:${project.groupId.path}/${project.artifactId}.properties");
		locations.add("classpath:${project.groupId.path}/env${deploy.env}.properties");
		return locations;
	}

}
