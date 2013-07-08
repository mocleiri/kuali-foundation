package org.kuali.common.impex.staging;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.util.ModelUtils;
import org.kuali.common.impex.schema.DumpSchemaExecutable;
import org.kuali.common.impex.schema.DumpSchemaService;
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

		// Load the existing schema, filter it, and persist it back to XML
		Schema schema = service.getSchema(inputSchemaFile);
		StringFilter filter = StringFilter.getInstance(includes, excludes);
		ModelUtils.filter(schema, filter);
		service.dumpSchema(schema, outputSchemaFile);
	}

}
