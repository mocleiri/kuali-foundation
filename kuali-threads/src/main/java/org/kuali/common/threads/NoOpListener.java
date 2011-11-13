package org.kuali.common.threads;


public class NoOpListener<T> implements ProgressListener<T> {

    @Override
    public void progressCompleted() {
        ; // do nothing
    }

    @Override
    public void progressStarted() {
        ; // do nothing
    }

    @Override
    public void progressOccurred(int count, int total, ProgressEvent<T> event) {
        ; // do nothing
    }

}
