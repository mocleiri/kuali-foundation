package org.kuali.common.threads;

public interface ElementHandler<T> {
    void handleElement(ListIteratorContext<T> context, int index, T element);
}
