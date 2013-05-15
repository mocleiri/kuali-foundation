package org.kuali.common.impex.model;

public class View implements NamedElement {

    protected String name;

    protected String queryString;

    public View(String n, String q) {
        name = n;
        queryString = q;
    }

    public String getName() {
        return name;
    }

    public String getQueryString() {
        return queryString;
    }

}
