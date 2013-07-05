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
import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DumpSchemaExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(DumpSchemaExecutable.class);

	public static final boolean DEFAULT_SKIP_EXECUTION = false;
	public static final DumpSchemaService DEFAULT_EXPORT_SCHEMA_SERVICE = new DefaultDumpSchemaService();

	boolean skip = DEFAULT_SKIP_EXECUTION;
	DumpSchemaService service = DEFAULT_EXPORT_SCHEMA_SERVICE;

	// Required
	File outputFile;
	Schema schema;

	// Optional
	File relativeDir;

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

		// The full file system path can sometimes be annoyingly long
		if (relativeDir != null) {
			logger.info("Creating - [{}]", FileSystemUtils.getRelativePath(relativeDir, outputFile));
		} else {
			logger.info("Creating - [{}]", LocationUtils.getCanonicalPath(outputFile));
		}

		// Persist the schema to disk as XML
		service.dumpSchema(schema, outputFile);

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

}
