package org.kuali.common.impex.model;

/**
 * This interface provides an implementation-independent API to access database column model information
 */
public interface Column {

    String getName();

    boolean isDateType();

    DataType getDataType();

    boolean isPrimaryKey();

    TypeSize getTypeSize();

    String getDefaultValue();

    boolean isTextType();

    boolean isNullable();

    String getDescription();

}
