/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.schema;

import java.io.File;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectSchemaExportExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ProjectSchemaExportExecutable.class);

	public static final boolean DEFAULT_EXECUTION_SKIP = false;
	public static final ExportSchemaService DEFAULT_EXPORT_SCHEMA_SERVICE = new DefaultExportSchemaService();

	boolean skip = DEFAULT_EXECUTION_SKIP;
	ExportSchemaService service = DEFAULT_EXPORT_SCHEMA_SERVICE;

	Project project;
	Schema schema;
	StringFilter nameFilter;
	File stagingDir;

	@Override
	public void execute() {

		// Might have nothing to do
		if (skip) {
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		Assert.notNull(schema, "schema is null");
		Assert.notNull(project, "project is null");
		Assert.notNull(stagingDir, "stagingDir is null");

		// Clone the existing schema but filter out model objects not relevant to this project
		Schema clone = ModelUtils.clone(schema, nameFilter);

		// The output file is always based on groupId + artifactId
		File outputFile = getOutputFile(stagingDir, project);

		// Log the name of the file we are creating
		logger.info("Creating - [{}]", FileSystemUtils.getRelativePath(stagingDir, outputFile));

		// Persist the cloned schema to disk as XML
		service.exportSchema(clone, outputFile);

	}

	protected File getOutputFile(File stagingDir, Project project) {
		String groupIdPath = project.getProperties().getProperty("project.groupId.base.path");
		String artifactId = project.getArtifactId();

		StringBuilder sb = new StringBuilder();
		sb.append(LocationUtils.getCanonicalPath(stagingDir));
		sb.append("/");
		sb.append(groupIdPath);
		sb.append("/");
		sb.append(artifactId);
		sb.append("/");
		sb.append("schema.xml");
		return new File(sb.toString());
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public ExportSchemaService getService() {
		return service;
	}

	public void setService(ExportSchemaService service) {
		this.service = service;
	}

	public StringFilter getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(StringFilter nameFilter) {
		this.nameFilter = nameFilter;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public File getStagingDir() {
		return stagingDir;
	}

	public void setStagingDir(File stagingDir) {
		this.stagingDir = stagingDir;
	}

}
