package org.kuali.common.impex.schema;

import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;

public interface SchemaSqlProducer {

    List<String> getTablesSql(List<Table> tables);

    List<String> getForeignKeySql(List<ForeignKey> foreignKeys);

    List<String> getSequencesSql(List<Sequence> sequences);

    List<String> getViewsSql(List<View> views);

    void setDataTypeMappingProvider(DataTypeMappingProvider dataTypeMappingProvider);

    DataTypeMappingProvider getDataTypeMappingProvider();
}
