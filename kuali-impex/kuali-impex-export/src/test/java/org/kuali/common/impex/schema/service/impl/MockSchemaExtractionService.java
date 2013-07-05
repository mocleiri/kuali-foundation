package org.kuali.common.impex.schema.service.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.service.SchemaDumpContext;
import org.kuali.common.impex.schema.service.SchemaDumpService;

public class MockSchemaExtractionService implements SchemaDumpService {

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
	public Schema getSchema(SchemaDumpContext context) {
		return schema;
	}

    @Override
    public List<ForeignKey> extractForeignKeys(List<String> tableNames, SchemaDumpContext context) throws SQLException {
        return schema.getForeignKeys();
    }

    @Override
    public List<Sequence> extractSequences(SchemaDumpContext context) throws SQLException {
        return schema.getSequences();
    }

    @Override
    public List<Table> extractTables(List<String> tableNames, SchemaDumpContext context) throws SQLException {
        return schema.getTables();
    }

    @Override
    public List<View> extractViews(SchemaDumpContext context) throws SQLException {
        return schema.getViews();
    }
}
