package org.kuali.common.threads.listener;

import java.io.PrintStream;

/**
 * Listener that prints a dot to System.out whenever progress is made.
 *
 * @param <T>
 */
public class ConsoleListener<T> implements ProgressListener<T> {

    PrintStream out = System.out;
    String startToken = ".";
    String completeToken = ".";
    String progressToken = ".";

    public ConsoleListener() {
        this(".", ".");
    }

    public ConsoleListener(String startToken, String progressToken) {
        super();
        this.startToken = startToken;
        this.progressToken = progressToken;
    }

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

    public PrintStream getOut() {
        return out;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }

    public String getStartToken() {
        return startToken;
    }

    public void setStartToken(String startToken) {
        this.startToken = startToken;
    }

    public String getCompleteToken() {
        return completeToken;
    }

    public void setCompleteToken(String completeToken) {
        this.completeToken = completeToken;
    }

    public String getProgressToken() {
        return progressToken;
    }

    public void setProgressToken(String progressToken) {
        this.progressToken = progressToken;
    }

}
