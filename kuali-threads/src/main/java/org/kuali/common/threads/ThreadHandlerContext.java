package org.kuali.common.threads;

import java.util.List;

import org.kuali.common.threads.listener.NoOpListener;
import org.kuali.common.threads.listener.ProgressListener;

public class ThreadHandlerContext<T> {
    int min;
    int max;
    int divisor;
    List<T> list;
    ElementHandler<T> handler;
    ProgressListener<T> listener = new NoOpListener<T>();

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public ElementHandler<T> getHandler() {
        return handler;
    }

    public void setHandler(ElementHandler<T> handler) {
        this.handler = handler;
    }

    public ProgressListener<T> getListener() {
        return listener;
    }

    public void setListener(ProgressListener<T> listener) {
        this.listener = listener;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

}
