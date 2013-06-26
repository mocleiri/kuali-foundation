package org.kuali.common.impex.schema.service.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;

public class MockSchemaExtractionService implements SchemaExtractionService {

    Schema schema;

    public MockSchemaExtractionService() {
        schema = new Schema();

        Table foo = new Table();
        foo.setName("FOO");

        Table bar = new Table();
        bar.setName("BAR");

        schema.setTables(Arrays.asList(foo, bar));
    }

	@Override
	public Schema getSchema(SchemaExtractionContext context) {
		return schema;
	}

    @Override
    public List<ForeignKey> extractForeignKeys(List<String> tableNames, SchemaExtractionContext context) throws SQLException {
        return schema.getForeignKeys();
    }

    @Override
    public List<Sequence> extractSequences(SchemaExtractionContext context) throws SQLException {
        return schema.getSequences();
    }

    @Override
    public Collection<Table> extractTables(List<String> tableNames, SchemaExtractionContext context) throws SQLException {
        return schema.getTables();
    }

    @Override
    public List<View> extractViews(SchemaExtractionContext context) throws SQLException {
        return schema.getViews();
    }
}
