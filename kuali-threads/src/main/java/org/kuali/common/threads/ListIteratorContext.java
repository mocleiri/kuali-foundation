package org.kuali.common.threads;

import java.util.List;

public class ListIteratorContext<T> {
    int id;
    int offset;
    int length;
    ThreadHandler<T> threadHandler;
    ProgressNotifier<T> notifier;
    List<T> list;
    ElementHandler<T> elementHandler;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ThreadHandler<T> getThreadHandler() {
        return threadHandler;
    }

    public void setThreadHandler(ThreadHandler<T> threadHandler) {
        this.threadHandler = threadHandler;
    }

    public ProgressNotifier<T> getNotifier() {
        return notifier;
    }

    public void setNotifier(ProgressNotifier<T> tracker) {
        this.notifier = tracker;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public ElementHandler<T> getElementHandler() {
        return elementHandler;
    }

    public void setElementHandler(ElementHandler<T> elementHandler) {
        this.elementHandler = elementHandler;
    }

}
