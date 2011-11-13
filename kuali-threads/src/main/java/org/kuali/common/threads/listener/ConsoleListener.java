package org.kuali.common.threads.listener;

import java.io.PrintStream;

/**
 * Listener that prints a dot to System.out whenever progress is made.
 *
 * By default, it also prints a Maven style log header when progress starts and a linefeed when progress completes.
 *
 * @param <T>
 */
public class ConsoleListener<T> implements ProgressListener<T> {

    PrintStream out = System.out;
    String startToken = "[INFO] Progress: ";
    String completeToken = "\n";
    String progressToken = ".";

    @Override
    public void progressCompleted() {
        out.print(completeToken);
    }

    @Override
    public void progressStarted() {
        out.print(startToken);
    }

    @Override
    public void progressOccurred(int count, int total, ProgressEvent<T> event) {
        out.print(progressToken);
    }

}
