package org.kuali.common.threads.listener;

/**
 * Prints a Maven style log header when progress starts a linefeed when progress completes and a dot whenever progress
 * occurs
 *
 * @param <T>
 */
public class MavenConsoleListener<T> extends ConsoleListener<T> {

    public MavenConsoleListener() {
        super();
        setStartToken("[INFO] Progress: ");
        setCompleteToken("\n");
    }

}
