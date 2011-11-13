package org.kuali.common.threads;

import java.lang.Thread.UncaughtExceptionHandler;

import org.kuali.common.threads.listener.ProgressNotifier;

/**
 * Handles the execution of threads. The executeThreads() will start all threads and then block until they have
 * completed. If an exception occurs in a client thread the uncaughtException() method of this ThreadHandler will be
 * invoked. The default behavior if that happens is to set the stopThreads flag to true and retain a handle to the
 * exception. Client threads should examine the isStopThreads() method of their handler and shutdown as quickly as
 * possible if that method returns true.
 *
 * The default behavior if an exception occurs in a client thread is for the handler to re-throw it once client threads
 * have completed. If that is not desired, set the rethrowException flag to false. The getException() method can still
 * be used to obtain the exception thrown in the client thread.
 *
 * @param <T>
 */
public class ThreadHandler<T> implements UncaughtExceptionHandler {

    ThreadGroup group;
    Thread[] threads;
    ThreadHandlerException exception;
    boolean stopThreads;
    int elementsPerThread;
    int threadCount;
    ProgressNotifier<T> notifier;
    boolean rethrowException = true;
    ExecutionStatistics executionStatistics;

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
        long start = System.currentTimeMillis();
        start();
        join();
        long millis = System.currentTimeMillis() - start;
        executionStatistics = new ExecutionStatistics();
        executionStatistics.setExecutionTime(millis);
        executionStatistics.setThreadCount(threadCount);
        executionStatistics.setIterationCount(notifier.getProgress());
        executionStatistics.setElementsPerThread(elementsPerThread);

        if (isThrowException()) {
            throw exception;
        }
    }

    protected boolean isThrowException() {
        return rethrowException && exception != null;
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
            throw new ThreadHandlerException(e);
        }
    }

    @Override
    public synchronized void uncaughtException(Thread t, Throwable e) {
        this.stopThreads = true;
        this.group.interrupt();
        long id = t.getId();
        String name = t.getName();
        this.exception = new ThreadHandlerException("Exception in thread [" + id + ":" + name + "]", e);
    }

    public synchronized boolean isStopThreads() {
        return stopThreads;
    }

    public ThreadHandlerException getException() {
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

    public boolean isRethrowException() {
        return rethrowException;
    }

    public void setRethrowException(boolean rethrowException) {
        this.rethrowException = rethrowException;
    }

    public ExecutionStatistics getExecutionStatistics() {
        return executionStatistics;
    }
}
