package org.kuali.common.impex.schema;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.util.DumpConstants;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.Assert;

public class CreateFilteredSchemaExecutable implements Executable {

	DumpSchemaService service = DumpSchemaExecutable.DEFAULT_EXPORT_SCHEMA_SERVICE;
	List<String> includes = Arrays.asList(DumpConstants.DEFAULT_INCLUDE);
	List<String> excludes = Arrays.asList(DumpConstants.DEFAULT_EXCLUDE);
	File inputSchemaFile;
	File outputSchemaFile;

	@Override
	public void execute() {
		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");
		Assert.notNull(inputSchemaFile, "inputSchemaFile is null");
		Assert.notNull(outputSchemaFile, "outputSchemaFile is null");
		Assert.isTrue(LocationUtils.exists(inputSchemaFile), "inputSchemaFile does not exist");

		// Load an existing schema, filter it, persist it back to the file system as XML
		Schema schema = service.getSchema(inputSchemaFile);
		StringFilter filter = StringFilter.getInstance(includes, excludes);
		ModelUtils.filter(schema, filter);
		service.dumpSchema(schema, outputSchemaFile);
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

	public File getInputSchemaFile() {
		return inputSchemaFile;
	}

	public void setInputSchemaFile(File inputSchemaFile) {
		this.inputSchemaFile = inputSchemaFile;
	}

	public File getOutputSchemaFile() {
		return outputSchemaFile;
	}

	public void setOutputSchemaFile(File outputSchemaFile) {
		this.outputSchemaFile = outputSchemaFile;
	}

}
