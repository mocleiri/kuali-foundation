package org.kuali.common.impex.schema;

import org.kuali.common.impex.model.Column;

public interface DataTypeMappingProvider {

    DataTypeMapping getDataTypeMapping(Column column);

}
