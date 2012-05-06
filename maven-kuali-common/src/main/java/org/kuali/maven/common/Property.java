package org.kuali.maven.common;

public class Property {

    public Property() {
        this(null);
    }

    public Property(String value) {
        super();
        this.key = value;
    }

    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String value) {
        this.key = value;
    }
}
