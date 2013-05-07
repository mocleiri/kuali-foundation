package org.kuali.common.impex.model;

import java.util.List;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
public interface Table extends DatabaseModelElement {

    List<Column> getColumns();
}
