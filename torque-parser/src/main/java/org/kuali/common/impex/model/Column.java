package org.kuali.common.impex.model;

/**
 * This interface provides an implementation-independent API to access database column model information
 */
public class Column implements NamedElement {

    protected String name;
    protected DataType columnDataType;
    protected Table table;
    protected TypeSize typeSize;
    protected String defaultValue;
    protected String description;

    protected boolean primaryKey;
    protected boolean nullable;

    /**
     * Create a new instance of a Column
     *
     * All values are initialized, with a special note that nullable is initially set to true
     */
    public Column(String n, DataType d, Table t) {
        name = n;
        columnDataType = d;
        table = t;

        primaryKey = false;
        typeSize = null;
        defaultValue = null;

        // As a more sensible default, set nullable to true
        nullable = true;
    }

    public DataType getDataType() {
        return columnDataType;
    }

    public String getName() {
        return name;
    }

    public Table getTable() {
        return table;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public TypeSize getTypeSize() {
        return typeSize;
    }

    public void setTypeSize(TypeSize typeSize) {
        this.typeSize = typeSize;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
