package org.kuali.common.impex.schema;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.Assert;

public class CopySchemaFileExecutable implements Executable {

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
		Assert.notNull(schemaInputFile, "schemaInputFile is null");
		Assert.notNull(schemaOutputFile, "schemaOutputFile is null");
		Assert.isTrue(LocationUtils.exists(schemaInputFile), "schemaInputFile does not exist");

		// Materialize a Schema object from the XML file
		Schema schema = service.getSchema(schemaInputFile);

		// Setup an executable that can filter and dump it back to disk correctly
		Executable exec = getDumpSchemaExecutable(this, schema);
		exec.execute();
	}

	protected Executable getDumpSchemaExecutable(CopySchemaFileExecutable exec, Schema schema) {
		DumpSchemaExecutable returnValue = new DumpSchemaExecutable();
		returnValue.setSchema(schema);
		returnValue.setIncludes(exec.getIncludes());
		returnValue.setExcludes(exec.getExcludes());
		returnValue.setOutputFile(exec.getSchemaOutputFile());
		returnValue.setRelativeDir(exec.getRelativeDir());
		return returnValue;
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
