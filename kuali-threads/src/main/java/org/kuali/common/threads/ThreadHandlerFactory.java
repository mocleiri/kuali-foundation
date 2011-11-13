package org.kuali.common.threads;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import org.kuali.common.threads.listener.ProgressNotifier;

/**
 * Produce ThreadHandlers that create and control execution of threads for iterating over List<T> objects
 */
public class ThreadHandlerFactory {


    /**
     * Given some context, produce a ThreadHandler for iterating over the list of elements provided in the context
     *
     * @param <T>
     * @param context
     * @return
     */
    public <T> ThreadHandler<T> getThreadHandler(ThreadHandlerContext<T> context) {
        // Extract some local variables for convenience
        List<T> list = context.getList();
        int elements = list.size();
        int max = context.getMax();
        int min = context.getMin();

        // Calculate # of threads and elements per thread
        int initialThreadCount = getInitialThreadCount(max, min, elements, context.getDivisor());
        int elementsPerThread = getElementsPerThread(initialThreadCount, elements);
        int adjustedThreadCount = getAdjustedThreadCount(initialThreadCount, elementsPerThread, elements);

        // Create a new thread handler
        ThreadHandler<T> handler = new ThreadHandler<T>();
        handler.setThreadCount(adjustedThreadCount);
        handler.setElementsPerThread(elementsPerThread);

        // Setup a notifier/listener for tracking progress
        ProgressNotifier<T> notifier = new ProgressNotifier<T>();
        notifier.setListener(context.getListener());
        notifier.setTotal(list.size());
        handler.setNotifier(notifier);

        // Create a thread group and threads
        ThreadGroup group = new ThreadGroup("List Iterator Threads");
        group.setDaemon(true);
        Thread[] threads = getThreads(handler, list, context.getHandler());

        // Store both in the handler
        handler.setGroup(group);
        handler.setThreads(threads);

        // Return the ThreadHandler
        return handler;
    }

    /**
     * Generate threads where each thread iterates over a portion of the list
     *
     * @param <T>
     * @param threadHandler
     * @param list
     * @param elementHandler
     * @return
     */
    protected <T> Thread[] getThreads(ThreadHandler<T> threadHandler, List<T> list, ElementHandler<T> elementHandler) {
        // Total threads we'll need
        Thread[] threads = new Thread[threadHandler.getThreadCount()];

        // Create each thread
        for (int i = 0; i < threads.length; i++) {

            // Get an identifier for this thread
            int id = i + 1;

            // Calculate an offset into the list for this thread
            int offset = i * threadHandler.getElementsPerThread();

            // Calculate how many elements this thread will iterate over
            int length = getLength(threadHandler.getElementsPerThread(), list.size(), offset);

            // Give this thread some context
            ListIteratorContext<T> context = new ListIteratorContext<T>(id, offset, length, list);
            context.setNotifier(threadHandler.getNotifier());
            context.setThreadHandler(threadHandler);
            context.setElementHandler(elementHandler);

            // Create a thread and store it in the array
            Runnable runnable = new ListIteratorThread<T>(context);
            threads[i] = getThread(runnable, id, threadHandler.getGroup(), threadHandler);
        }

        // Return an array of threads ready to execute
        return threads;
    }

    /**
     * Return elementsPerThread unless that would run us out past the end of the list. In that case, adjust length so we
     * go to the end of the list
     *
     * @param elementsPerThread
     * @param size
     * @param offset
     * @return
     */
    protected int getLength(int elementsPerThread, int size, int offset) {
        int length = elementsPerThread;
        if (offset + length > size) {
            length = size - offset;
        }
        return length;
    }

    /**
     * Construct a Thread from the information provided
     *
     * @param <T>
     * @param runnable
     * @param id
     * @param group
     * @param handler
     * @return
     */
    protected <T> Thread getThread(Runnable runnable, int id, ThreadGroup group, UncaughtExceptionHandler handler) {
        Thread thread = new Thread(group, runnable, "ListIterator-" + id);
        thread.setUncaughtExceptionHandler(handler);
        thread.setDaemon(true);
        return thread;
    }

    /**
     * Come up with an initial calculation for the number of threads to use. The number returned here will never be
     * greater than max. It may be less than min, but only in the case where elements is also less than min.
     *
     * @param max
     * @param min
     * @param elements
     * @param divisor
     * @return
     */
    protected int getInitialThreadCount(int max, int min, int elements, int divisor) {

        // Reduce max if appropriate
        if (max > elements) {
            max = elements;
        }

        // Reduce min if appropriate
        if (min > elements) {
            min = elements;
        }

        // Start off aggressive
        int threads = max;

        // Divisor allows clients to scale threads in proportion to the number of elements in the list
        if (divisor > 0) {
            threads = elements / divisor;
        }

        // Reset to max if we have exceeded it
        if (threads > max) {
            threads = max;
        }

        // Reset to min if we have dropped below it
        if (threads < min) {
            threads = min;
        }

        // Return the thread count to use
        return threads;
    }

    /**
     * Return the minimum number of elements that must be assigned to each thread in order to cover every element
     *
     * @param threads
     * @param elements
     * @return
     */
    protected int getElementsPerThread(int threads, int elements) {
        int elementsPerThread = elements / threads;
        while (elementsPerThread * threads < elements) {
            elementsPerThread++;
        }
        return elementsPerThread;
    }

    /**
     * Given fixed numbers for elements and elemensPerThread, return the minimum number of threads needed to cover every
     * element
     *
     * @param threads
     * @param elementsPerThread
     * @param elements
     * @return
     */
    protected int getAdjustedThreadCount(int threads, int elementsPerThread, int elements) {
        while (elementsPerThread * (threads - 1) > elements) {
            threads--;
        }
        return threads;
    }

}
