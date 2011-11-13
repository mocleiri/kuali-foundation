package org.kuali.common.threads;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadInvoker {
    private final Logger logger = LoggerFactory.getLogger(ThreadHandlerFactory.class);
    ThreadHandlerFactory factory = new ThreadHandlerFactory();

    public <T> ExecutionStatistics invokeThreads(ThreadHandlerContext<T> context) {
        return invokeThreads(context, "Executing ");
    }

    public <T> ExecutionStatistics invokeThreads(ThreadHandlerContext<T> context, String msg) {
        if (context.getHandler() == null || context.getList() == null) {
            throw new IllegalArgumentException("Neither elementHandler nor list can be null");
        }
        ThreadHandler<T> handler = factory.getThreadHandler(context);
        int elementsPerThread = handler.getElementsPerThread();
        int size = context.getList().size();
        logger.info(msg + "[t:" + handler.getThreadCount() + " e:" + elementsPerThread + " s:" + size + "]");
        handler.executeThreads();
        return handler.getExecutionStatistics();
    }

    public <T> ExecutionStatistics invokeThreads(int max, int min, int divisor, ThreadHandlerContext<T> context) {
        context.setMax(max);
        context.setMin(min);
        context.setDivisor(divisor);
        return invokeThreads(context);
    }

    public <T> ExecutionStatistics invokeThreads(int max, ElementHandler<T> elementHandler, List<T> list) {
        ThreadHandlerContext<T> thc = new ThreadHandlerContext<T>();
        thc.setMax(max);
        thc.setHandler(elementHandler);
        thc.setList(list);
        return invokeThreads(max, 0, 0, thc);
    }

}
