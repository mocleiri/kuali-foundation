package org.kuali.maven.plugins.jenkins.helper;

/**
 * The worlds simplest counter
 */
public class Counter {
    private static long count = 0;

    public static final synchronized long increment() {
        return count++;
    }

}
