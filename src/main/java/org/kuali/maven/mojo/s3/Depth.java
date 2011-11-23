package org.kuali.maven.mojo.s3;

public class Depth {
    int depth;

    public void increment() {
        depth++;
    }

    public void decrement() {
        depth--;
    }

    public int getValue() {
        return depth;
    }

}
