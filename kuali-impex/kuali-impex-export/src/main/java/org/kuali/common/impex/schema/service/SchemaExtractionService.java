package org.kuali.common.impex.schema.service;

import org.kuali.common.impex.model.Schema;

/**
 * This class defines an API to extract schema information from a live db
 *
 * @author andrewlubbers
 */
public interface SchemaExtractionService {

    public Schema getSchema(SchemaExtractionContext context);

}
