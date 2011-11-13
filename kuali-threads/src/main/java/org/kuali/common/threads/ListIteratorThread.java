package org.kuali.common.threads;

import java.util.List;

public class ListIteratorThread<T> implements Runnable {

    ListIteratorContext<T> context;

    @Override
    public void run() {
        int offset = context.getOffset();
        int length = context.getLength();
        List<T> list = context.getList();
        ElementHandler<T> handler = context.getElementHandler();
        for (int i = offset; i < offset + length; i++) {
            if (context.getThreadHandler().isStopThreads()) {
                break;
            }
            T element = list.get(i);
            handler.handleElement(context, i, element);
            ProgressEvent<T> event = new ProgressEvent<T>();
            event.setElement(element);
            event.setIndex(i);
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
