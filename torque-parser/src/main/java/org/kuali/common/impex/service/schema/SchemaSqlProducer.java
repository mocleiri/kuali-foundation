package org.kuali.common.impex.service.schema;

import java.util.List;

import org.kuali.common.impex.model.Table;

public interface SchemaSqlProducer {

    List<String> getTablesSql(List<Table> tables);

    //List<String> getConstraintsSql(List<Constraint> constraints);

    //List<String> getSequencesSql(List<Sequence> sequences);

    //List<String> getViewsSql(List<Vew> views);

}
