package org.kuali.common.impex.model.compare.service;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.compare.SchemaCompareResult;

public interface SchemaCompareService {

    SchemaCompareResult compare(Schema schema1, Schema schema2);

}
