package org.kuali.common.threads;

import java.util.List;

import org.kuali.common.threads.listener.ConsoleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadInvoker {
    private final Logger logger = LoggerFactory.getLogger(ThreadHandlerFactory.class);

    public <T> ExecutionStatistics invokeThreads(int max, int divisor, ElementHandler<T> elementHandler, List<T> list) {
        ThreadHandlerFactory factory = new ThreadHandlerFactory();
        ThreadHandlerContext<T> thc = new ThreadHandlerContext<T>();
        thc.setMax(max);
        thc.setHandler(elementHandler);
        thc.setList(list);
        thc.setDivisor(divisor);
        thc.setListener(new ConsoleListener<T>());
        ThreadHandler<T> handler = factory.getThreadHandler(thc);
        int elementsPerThread = handler.getElementsPerThread();
        int size = list.size();
        logger.info("Executing [t:" + handler.getThreadCount() + " e:" + elementsPerThread + " s:" + size + "]");
        handler.executeThreads();
        return handler.getExecutionStatistics();
    }

    public <T> void invokeThreads(int max, ElementHandler<T> elementHandler, List<T> list) {
        invokeThreads(max, 0, elementHandler, list);
    }

}
