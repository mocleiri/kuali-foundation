package org.kuali.common.impex.model;

import java.util.List;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
public interface Table {

    String getName();

    List<Column> getColumns();

    List<UniqueConstraint> getUniqueConstraints();

    List<Index> getIndices();

    String getDescription();
}
