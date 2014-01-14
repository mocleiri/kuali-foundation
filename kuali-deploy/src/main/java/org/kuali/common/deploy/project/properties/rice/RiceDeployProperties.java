package org.kuali.common.deploy.project.properties.rice;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.deploy.project.DeployProjectConstants;
import org.kuali.common.util.project.model.ProjectIdentifier;
import org.kuali.common.util.properties.model.ProjectResource;

public enum RiceDeployProperties implements ProjectResource {

	DB("rice/db.properties"), //
	INIT_SOURCE_DB("rice/initialize-source-db.properties"); //

	private RiceDeployProperties(String filename) {
		checkArgument(!StringUtils.isBlank(filename));
		this.filename = filename;
	}

	private final ProjectIdentifier project = DeployProjectConstants.ID;
	private final String filename;

	@Override
	public ProjectIdentifier getProject() {
		return project;
	}

	@Override
	public String getFilename() {
		return filename;
	}

}
