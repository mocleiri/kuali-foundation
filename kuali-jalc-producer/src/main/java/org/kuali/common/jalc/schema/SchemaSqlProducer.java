package org.kuali.common.jalc.schema;

import java.util.List;

import org.kuali.common.jalc.model.ForeignKey;
import org.kuali.common.jalc.model.Sequence;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.jalc.model.View;

public interface SchemaSqlProducer {

    List<String> getTablesSql(List<Table> tables);

    List<String> getForeignKeySql(List<ForeignKey> foreignKeys);

    List<String> getSequencesSql(List<Sequence> sequences);

    List<String> getViewsSql(List<View> views);

}
