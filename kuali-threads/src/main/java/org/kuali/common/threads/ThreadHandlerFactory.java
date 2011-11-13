package org.kuali.common.threads;

import java.util.List;

public class ThreadHandlerFactory {

    public <T> ThreadHandler<T> getThreadHandler(ThreadHandlerContext<T> context) {
        List<T> list = context.getList();
        int max = context.getMax();
        int min = context.getMin();
        int actualThreadCount = getActualThreadCount(max, min, list.size(), context.getDivisor());
        int elementsPerThread = getElementsPerThread(actualThreadCount, list.size());
        while (elementsPerThread * (actualThreadCount - 1) > list.size()) {
            actualThreadCount--;
        }
        ThreadHandler<T> handler = new ThreadHandler<T>();
        handler.setThreadCount(actualThreadCount);
        handler.setElementsPerThread(elementsPerThread);
        ProgressNotifier<T> notifier = new ProgressNotifier<T>();
        notifier.setListener(context.getListener());
        notifier.setTotal(list.size());
        handler.setNotifier(notifier);
        ThreadGroup group = new ThreadGroup("List Iterator Threads");
        group.setDaemon(true);
        handler.setGroup(group);
        Thread[] threads = getThreads(handler, list, context.getHandler());
        handler.setThreads(threads);
        return handler;
    }

    protected int getActualThreadCount(int maxThreads, int minThreads, int elementCount, int divisor) {

        if (maxThreads > elementCount) {
            // If there are fewer elements than max threads, drop maxThreads down to elementCount
            maxThreads = elementCount;
        }

        if (minThreads > elementCount) {
            // If there are fewer elements than min threads, drop minThreads down to elementCount
            minThreads = elementCount;
        }
        int actualThreadCount = maxThreads;
        if (divisor > 0) {
            actualThreadCount = elementCount / divisor;
        }
        if (actualThreadCount > maxThreads) {
            actualThreadCount = maxThreads;
        }
        if (actualThreadCount < minThreads) {
            actualThreadCount = minThreads;
        }
        return actualThreadCount;
    }

    protected int getElementsPerThread(int threads, int elements) {
        int elementsPerThread = elements / threads;
        while (elementsPerThread * threads < elements) {
            elementsPerThread++;
        }
        return elementsPerThread;
    }

    protected <T> Thread[] getThreads(ThreadHandler<T> threadHandler, List<T> list, ElementHandler<T> elementHandler) {
        Thread[] threads = new Thread[threadHandler.getThreadCount()];
        for (int i = 0; i < threads.length; i++) {
            int offset = i * threadHandler.getElementsPerThread();
            int length = threadHandler.getElementsPerThread();
            if (offset + length > list.size()) {
                length = list.size() - offset;
            }
            ListIteratorContext<T> context = new ListIteratorContext<T>();
            context.setList(list);
            context.setNotifier(threadHandler.getNotifier());
            context.setOffset(offset);
            context.setLength(length);
            context.setThreadHandler(threadHandler);
            context.setElementHandler(elementHandler);
            int id = i + 1;
            context.setId(id);
            ListIteratorThread<T> thread = new ListIteratorThread<T>();
            thread.setContext(context);

            threads[i] = new Thread(threadHandler.getGroup(), thread, "ListIterator-" + id);
            threads[i].setUncaughtExceptionHandler(threadHandler);
            threads[i].setDaemon(true);
        }
        return threads;
    }

}
