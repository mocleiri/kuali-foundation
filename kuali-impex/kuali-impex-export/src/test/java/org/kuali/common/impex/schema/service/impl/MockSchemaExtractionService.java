package org.kuali.common.impex.schema.service.impl;

import java.util.Arrays;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;

public class MockSchemaExtractionService implements SchemaExtractionService {

	@Override
	public Schema getSchema(SchemaExtractionContext context) {
		Schema schema = new Schema();

		Table foo = new Table();
		foo.setName("FOO");

		Table bar = new Table();
		bar.setName("BAR");

		schema.setTables(Arrays.asList(foo, bar));

		return schema;
	}

}
