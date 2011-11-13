package org.kuali.common.threads.listener;

/**
 * Listen for events related to making progress towards an overall total.
 *
 * @param <T>
 */
public interface ProgressListener<T> {

    /**
     * Progress has begun
     */
    void progressStarted();

    /**
     * Progress towards the total has occurred
     *
     * @param count
     * @param total
     * @param event
     */
    void progressOccurred(int count, int total, ProgressEvent<T> event);

    /**
     * Progress is now complete
     */
    void progressCompleted();
}
