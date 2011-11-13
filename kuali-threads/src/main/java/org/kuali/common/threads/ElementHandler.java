package org.kuali.common.threads;

/**
 * Callback for handling one element from a list
 *
 * @param <T>
 */
public interface ElementHandler<T> {
    void handleElement(ListIteratorContext<T> context, int index, T element);
}
