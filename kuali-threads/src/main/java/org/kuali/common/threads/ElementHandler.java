package org.kuali.common.threads;

/**
 * Callback for handling an iteration over an element from a list
 *
 * @param <T>
 */
public interface ElementHandler<T> {
    void handleElement(ListIteratorContext<T> context, int index, T element);
}
