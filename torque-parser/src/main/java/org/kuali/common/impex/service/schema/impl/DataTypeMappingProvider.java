package org.kuali.common.impex.service.schema.impl;

import org.kuali.common.impex.model.Column;

public interface DataTypeMappingProvider {

    DataTypeMapping getDataTypeMapping(Column column);

}
