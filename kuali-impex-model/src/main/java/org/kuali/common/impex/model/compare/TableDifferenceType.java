package org.kuali.common.impex.model.compare;

public enum TableDifferenceType {
    TABLE_DESCRIPTION("Table Description"),
    MISSING_TABLE("Missing Table"),
    COLUMN_DATA_TYPE("Column Data Type"),
    COLUMN_PRIMARY_KEY("Column Primary Key"),
    COLUMN_NULLABLE("Column Nullable"),
    COLUMN_DATA_TYPE_SIZE("Column Data Type Size"),
    COLUMN_DESCRIPTION("Column Description"),
    COLUMN_DEFAULT_VALUE("Column Default Value"),
    MISSING_COLUMN("Missing Column"),
    UNIQUE_CONSTRAINT_COLUMNS("Unique Constraint Columns"),
    MISSING_UNIQUE_CONSTRAINT("Missing Unique Constraint"),
    INDEX_COLUMNS("Index Columns"),
    INDEX_UNIQUE("Index Uniqueness"),
    MISSING_INDEX("Missing Index");

    private String label;

    private TableDifferenceType(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
