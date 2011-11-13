package org.kuali.common.threads.listener;

public interface ProgressListener<T> {
    void progressStarted();

    void progressOccurred(int count, int total, ProgressEvent<T> event);

    void progressCompleted();
}
