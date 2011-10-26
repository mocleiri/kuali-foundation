package org.kuali.spring.util.event;

public class ValueResolutionEvent {
    Object oldValue;
    Object newValue;

    public ValueResolutionEvent() {
        this(null, null);
    }

    public ValueResolutionEvent(Object oldValue, Object newValue) {
        super();
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }
}
