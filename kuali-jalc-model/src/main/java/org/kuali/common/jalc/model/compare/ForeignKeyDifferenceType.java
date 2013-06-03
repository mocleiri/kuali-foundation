package org.kuali.common.jalc.model.compare;

public enum ForeignKeyDifferenceType {
    MISSING_FOREIGN_KEY("Missing Foreign Key"),
    FOREIGN_TABLE("Foreign Table"),
    LOCAL_COLUMNS("Local Columns"),
    FOREIGN_COLUMNS("Foreign Columns"),
    ON_DELETE("On Delete"),
    ON_UPDATE("On Update"),
    LOCAL_TABLE("Local Table");

    private String label;

    private ForeignKeyDifferenceType(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
