package org.kuali.spring.util;

public class PropertyMergeResult {
    PropertiesMergeContext context;
    String key;
    String oldValue;
    String newValue;
    PropertyMergeType type;

    public PropertyMergeResult() {
        this(null, null, null, null, null);
    }

    public PropertyMergeResult(PropertiesMergeContext context, String key, String oldValue, String newValue,
            PropertyMergeType reason) {
        super();
        this.context = context;
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.type = reason;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public PropertyMergeType getType() {
        return type;
    }

    public void setType(PropertyMergeType reason) {
        this.type = reason;
    }

    public PropertiesMergeContext getContext() {
        return context;
    }

    public void setContext(PropertiesMergeContext context) {
        this.context = context;
    }
}
