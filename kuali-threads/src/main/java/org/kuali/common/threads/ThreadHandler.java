package org.kuali.common.threads;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadHandler<T> implements UncaughtExceptionHandler {

    ThreadGroup group;
    Thread[] threads;
    Exception exception;
    boolean stopThreads;
    int elementsPerThread;
    int threadCount;
    ProgressNotifier<T> notifier;

    public ThreadGroup getGroup() {
        return group;
    }

    public void setGroup(ThreadGroup group) {
        this.group = group;
    }

    public Thread[] getThreads() {
        return threads;
    }

    public void setThreads(Thread[] threads) {
        this.threads = threads;
    }

    public void executeThreads() {
        start();
        join();
    }

    protected void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    protected void join() {
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void uncaughtException(Thread t, Throwable e) {
        this.stopThreads = true;
        group.interrupt();
        this.exception = new RuntimeException("Unexpected issue in thread [" + t.getId() + ":" + t.getName() + "]", e);
    }

    public synchronized boolean isStopThreads() {
        return stopThreads;
    }

    public Exception getException() {
        return exception;
    }

    public int getElementsPerThread() {
        return elementsPerThread;
    }

    public void setElementsPerThread(int requestsPerThread) {
        this.elementsPerThread = requestsPerThread;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public ProgressNotifier<T> getNotifier() {
        return notifier;
    }

    public void setNotifier(ProgressNotifier<T> notifier) {
        this.notifier = notifier;
    }
}
