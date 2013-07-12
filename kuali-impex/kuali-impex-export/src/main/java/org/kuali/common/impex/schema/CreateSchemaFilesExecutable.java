package org.kuali.common.impex.schema;

import java.util.List;

import org.kuali.common.util.execute.Executable;

public class CreateSchemaFilesExecutable implements Executable {

	DumpSchemaService service = DumpSchemaExecutable.DEFAULT_EXPORT_SCHEMA_SERVICE;
	List<SchemaFileRequest> requests;

	@Override
	public void execute() {
	}

}
