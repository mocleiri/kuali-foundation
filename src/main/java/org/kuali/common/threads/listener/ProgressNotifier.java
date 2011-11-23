package org.kuali.common.threads.listener;

/**
 * Keeps track of overall progress towards a goal.
 *
 * Notifies its listener whenever progress has occurred.
 *
 * Also notifies the listener when progress starts and completes.
 */
public class ProgressNotifier<T> {

    ProgressListener<T> listener = new NoOpListener<T>();
    int progress;
    int total;

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void notify(ProgressEvent<T> event) {
        if (progress == 0) {
            listener.progressStarted();
        }
        progress++;
        listener.progressOccurred(progress, total, event);
        if (progress == total) {
            listener.progressCompleted();
        }
    }

    public ProgressListener<T> getListener() {
        return listener;
    }

    public void setListener(ProgressListener<T> listener) {
        this.listener = listener;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
