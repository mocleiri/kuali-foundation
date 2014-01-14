package org.kuali.common.deploy.resources;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.deploy.project.DeployProjectConstants;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.ProjectResource;

public enum RiceDeployProperties {

	DB(ProjectResource.create(DeployProjectConstants.ID, "rice/db.properties")), //
	INIT_SOURCE_DB(ProjectResource.create(DeployProjectConstants.ID, "rice/initialize-source-db.properties")); //

	private RiceDeployProperties(ProjectResource resource) {
		checkNotNull(resource, "'resource' cannot be null");
		this.resource = resource;
		String path = ProjectUtils.getPath(resource);
		checkArgument(LocationUtils.exists(path), "[%s] does not exist", path);
	}

	private final ProjectResource resource;

	public ProjectResource getResource() {
		return resource;
	}

}
