package org.kuali.common.jalc.model.compare;

public enum SequenceDifferenceType {
    START_VALUE("Start Value"),
    MISSING_SEQUENCE("Missing Sequence");

    private String label;

    private SequenceDifferenceType(String s) {
        this.label = s;
    }

    public String getLabel() {
        return label;
    }
}
