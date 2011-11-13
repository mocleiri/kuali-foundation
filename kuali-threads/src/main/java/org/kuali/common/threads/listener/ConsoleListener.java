package org.kuali.common.threads.listener;

import java.io.PrintStream;

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
