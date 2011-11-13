package org.kuali.common.threads;

import java.util.List;

import org.kuali.common.threads.listener.ProgressEvent;

/**
 * Thread for iterating over the portion of a list determined by offset and length. An ElementHandler is invoked for
 * each element
 *
 * @param <T>
 */
public class ListIteratorThread<T> implements Runnable {

    // The context for the iteration
    ListIteratorContext<T> context;

    public ListIteratorThread() {
        this(null);
    }

    public ListIteratorThread(ListIteratorContext<T> context) {
        super();
        this.context = context;
    }

    @Override
    public void run() {
        // Extract some local variables for convenience
        int offset = context.getOffset();
        int length = context.getLength();
        List<T> list = context.getList();
        ElementHandler<T> handler = context.getElementHandler();

        for (int i = offset; i < offset + length; i++) {
            // Something has gone wrong, shutdown immediately
            if (context.getThreadHandler().isStopThreads()) {
                break;
            }

            // Pull out an element
            T element = list.get(i);

            // Let the ElementHandler do something with it
            handler.handleElement(context, i, element);

            // Create a progress event each time an element gets handled
            ProgressEvent<T> event = new ProgressEvent<T>();
            event.setElement(element);
            event.setIndex(i);

            // Fire the notifier
            context.getNotifier().notify(event);
        }
    }

    public ListIteratorContext<T> getContext() {
        return context;
    }

    public void setContext(ListIteratorContext<T> context) {
        this.context = context;
    }

}
