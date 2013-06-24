package org.kuali.common.impex.schema.service.impl;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;

public class MockSchemaExtractionService implements SchemaExtractionService {

	@Override
	public Schema getSchema(SchemaExtractionContext context) {
		return new Schema();
	}

}
