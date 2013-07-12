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
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.schema.DefaultDumpSchemaService;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DumpSchemaExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(DumpSchemaExecutable.class);

	public static final boolean DEFAULT_SKIP_EXECUTION = false;
	public static final boolean DEFAULT_LOG_EXCLUDED_SCHEMA_OBJECTS = false;
	public static final String DEFAULT_EXCLUDED_SCHEMA_OBJECTS_TABLE_TILE = "Excluded Schema Objects";
	public static final DumpSchemaService DEFAULT_EXPORT_SCHEMA_SERVICE = new DefaultDumpSchemaService();

	boolean skip = DEFAULT_SKIP_EXECUTION;
	boolean logExcludedSchemaObjects = DEFAULT_LOG_EXCLUDED_SCHEMA_OBJECTS;
	String excludedSchemaObjectsTableTitle = DEFAULT_EXCLUDED_SCHEMA_OBJECTS_TABLE_TILE;
	DumpSchemaService service = DEFAULT_EXPORT_SCHEMA_SERVICE;

	// Required
	File outputFile;
	Schema schema;

	// Optional
	File relativeDir;
	List<String> includes = Arrays.asList(DumpConstants.DEFAULT_REGEX_INCLUDE);
	List<String> excludes = Arrays.asList(DumpConstants.DEFAULT_REGEX_EXCLUDE);

	@Override
	public void execute() {

		// Might have nothing to do
		if (skip) {
			logger.debug("Skipping execution");
			return;
		}

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		Assert.notNull(schema, "schema is null");
		Assert.notNull(outputFile, "outputFile is null");

		// Create a filter from the includes/excludes they supplied
		StringFilter filter = StringFilter.getInstance(includes, excludes);

		// Clone the schema so the act of dumping it to disk does not alter the original schema object they gave us in any way
		Schema clone = new Schema(schema);

		// Filter the schema and keep track of any schema objects that got filtered out
		Schema excludedSchemaObjects = ModelUtils.filter(clone, filter);

		// The full file system path can sometimes be annoyingly long
		String path = FileSystemUtils.getRelativePathQuietly(relativeDir, outputFile);

		// Show what we are up to
		logger.info("Creating - [{}] - {}", path, LoggerUtils.getLogMsg(includes, excludes));

		// Log the objects that go filtered out if they asked us to
		if (logExcludedSchemaObjects) {
			ModelUtils.logTable(excludedSchemaObjects, excludedSchemaObjectsTableTitle);
		}

		// Persist the schema to disk as XML
		service.dumpSchema(clone, outputFile);

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

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(File relativeDir) {
		this.relativeDir = relativeDir;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public boolean isLogExcludedSchemaObjects() {
		return logExcludedSchemaObjects;
	}

	public void setLogExcludedSchemaObjects(boolean logExcludedSchemaObjects) {
		this.logExcludedSchemaObjects = logExcludedSchemaObjects;
	}

	public String getExcludedSchemaObjectsTableTitle() {
		return excludedSchemaObjectsTableTitle;
	}

	public void setExcludedSchemaObjectsTableTitle(String excludedSchemaObjectsTableTitle) {
		this.excludedSchemaObjectsTableTitle = excludedSchemaObjectsTableTitle;
	}

}
