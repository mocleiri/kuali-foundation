package org.kuali.common.impex.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
public class Table implements NamedElement {

    protected String name;
    protected List<Column> columns;
    protected List<UniqueConstraint> uniqueConstraints;
    protected List<Index> indices;
    protected String description;

    public Table(String n) {
        name = n;
        columns = new ArrayList<Column>();
        uniqueConstraints = new ArrayList<UniqueConstraint>();
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<UniqueConstraint> getUniqueConstraints() {
        return uniqueConstraints;
    }

    public String getName() {
        return name;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Index> getIndices() {
        return indices;
    }

    public void setIndices(List<Index> indices) {
        this.indices = indices;
    }

    public void setUniqueConstraints(List<UniqueConstraint> uniqueConstraints) {
        this.uniqueConstraints = uniqueConstraints;
    }
}
