package org.kuali.common.impex.schema;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class CreateFilteredSchemaExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(CreateFilteredSchemaExecutable.class);

	DumpSchemaService service = DumpSchemaExecutable.DEFAULT_EXPORT_SCHEMA_SERVICE;
	List<String> includes = Arrays.asList(DumpConstants.DEFAULT_INCLUDE);
	List<String> excludes = Arrays.asList(DumpConstants.DEFAULT_EXCLUDE);
	File schemaInputFile;
	File schemaOutputFile;
	File relativeDir;

	@Override
	public void execute() {
		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		Assert.notNull(schemaInputFile, "inputSchemaFile is null");
		Assert.notNull(schemaOutputFile, "outputSchemaFile is null");
		Assert.isTrue(LocationUtils.exists(schemaInputFile), "inputSchemaFile does not exist");

		// Load an existing schema, filter it, persist it back to the file system as XML
		Schema schema = service.getSchema(schemaInputFile);
		StringFilter filter = StringFilter.getInstance(includes, excludes);
		ModelUtils.filter(schema, filter);

		if (FileSystemUtils.isParent(relativeDir, schemaOutputFile)) {
			logger.info("Creating - [{}]", FileSystemUtils.getRelativePath(relativeDir, schemaOutputFile));
		} else {
			logger.info("Creating - [{}]", LocationUtils.getCanonicalPath(schemaOutputFile));
		}

		service.dumpSchema(schema, schemaOutputFile);
	}

	public DumpSchemaService getService() {
		return service;
	}

	public void setService(DumpSchemaService service) {
		this.service = service;
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

	public File getSchemaInputFile() {
		return schemaInputFile;
	}

	public void setSchemaInputFile(File inputSchemaFile) {
		this.schemaInputFile = inputSchemaFile;
	}

	public File getSchemaOutputFile() {
		return schemaOutputFile;
	}

	public void setSchemaOutputFile(File outputSchemaFile) {
		this.schemaOutputFile = outputSchemaFile;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(File relativeDir) {
		this.relativeDir = relativeDir;
	}

}
