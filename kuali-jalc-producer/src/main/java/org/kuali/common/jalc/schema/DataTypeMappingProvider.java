package org.kuali.common.jalc.schema;

import org.kuali.common.jalc.model.Column;

public interface DataTypeMappingProvider {

    DataTypeMapping getDataTypeMapping(Column column);

}
