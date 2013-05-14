package org.kuali.common.impex.model;

import java.util.List;
import java.util.Map;

public interface ModelProvider {
    List<ForeignKey> getForeignKeys();

    List<Sequence> getSequences();

    Map<String, List<ForeignKey>> getTableNameToForeignKeys();

    List<Table> getTables();

    List<View> getViews();
}
