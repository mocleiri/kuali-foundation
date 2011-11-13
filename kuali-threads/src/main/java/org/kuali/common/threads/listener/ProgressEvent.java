package org.kuali.common.threads.listener;

import java.util.List;

/**
 * Contains information about a progress event. The list of elements that are being processed, the index of the element
 * that was just processed, and the element itself.
 *
 * @param <T>
 */
public class ProgressEvent<T> {
    // The list of elements to process before progress is complete
    List<T> list;

    // The index to the element in the list that was just processed
    int index;

    // The element that was processed
    T element;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

}
