package org.kuali.common.util.ant;

public class Counter {
    int value;

    public Counter() {
        this(0);
    }

    public Counter(int value) {
        super();
        this.value = value;
    }

    public synchronized int increment() {
        return ++value;
    }

    public synchronized int decrement() {
        return --value;
    }

    public synchronized int getValue() {
        return value;
    }

}
