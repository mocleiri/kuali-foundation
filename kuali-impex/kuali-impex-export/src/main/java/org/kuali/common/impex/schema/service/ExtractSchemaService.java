package org.kuali.common.impex.schema.service;

import java.sql.SQLException;
import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;

/**
 * This class defines an API to extract schema information from a live db
 * 
 * @author alubbers
 * @author jcaddel
 */
public interface ExtractSchemaService {

	Schema getSchema(ExtractSchemaContext context);

	List<Table> extractTables(List<String> tableNames, ExtractSchemaContext context) throws SQLException;

	List<View> extractViews(ExtractSchemaContext context) throws SQLException;

	List<Sequence> extractSequences(ExtractSchemaContext context) throws SQLException;

	List<ForeignKey> extractForeignKeys(List<String> tableNames, ExtractSchemaContext context) throws SQLException;
}
