package org.kuali.common.impex.model;

/**
 * This interface provides an implementation-independent API to access database column model information
 */
public interface Column extends DatabaseModelElement {

    boolean isDateType();

    DataType getDataType();

    boolean isPrimaryKey();
}
