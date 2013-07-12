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

package org.kuali.common.impex.schema.execute;

import java.io.File;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.schema.DefaultDumpSchemaService;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectSchemaExportExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ProjectSchemaExportExecutable.class);

	public static final boolean DEFAULT_EXECUTION_SKIP = false;
	public static final DumpSchemaService DEFAULT_EXPORT_SCHEMA_SERVICE = new DefaultDumpSchemaService();
	public static final String DEFAULT_SCHEMA_FILENAME = "schema.xml";

	boolean skip = DEFAULT_EXECUTION_SKIP;
	DumpSchemaService service = DEFAULT_EXPORT_SCHEMA_SERVICE;
	String schemaFilename = DEFAULT_SCHEMA_FILENAME;

	Project project;
	Schema schema;
	StringFilter nameFilter;
	File stagingDir;
	File basedir;

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
		Assert.notNull(basedir, "basedir is null");

		// Clone the existing schema
		Schema clone = new Schema(schema);

		// Filter out model objects not relevant to this export
		ModelUtils.filter(clone, nameFilter);

		// The location of the schema file is always based on groupId + artifactId
		File schemaFile = ProjectUtils.getResourceFile(stagingDir, project, schemaFilename);

		// Log the name of the file we are creating
		logger.info("Creating - [{}]", FileSystemUtils.getRelativePathQuietly(basedir, schemaFile));

		// Persist the cloned and filtered schema to disk as XML
		service.dumpSchema(clone, schemaFile);

	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public DumpSchemaService getService() {
		return service;
	}

	public void setService(DumpSchemaService service) {
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

	public File getBasedir() {
		return basedir;
	}

	public void setBasedir(File basedir) {
		this.basedir = basedir;
	}

}
