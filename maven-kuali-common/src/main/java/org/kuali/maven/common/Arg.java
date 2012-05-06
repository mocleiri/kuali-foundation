package org.kuali.maven.common;

public class Arg {

    public Arg() {
        this(null);
    }

    public Arg(String value) {
        super();
        this.value = value;
    }

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
