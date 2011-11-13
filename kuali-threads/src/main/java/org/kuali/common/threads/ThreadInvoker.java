package org.kuali.common.threads;

import java.util.List;

import org.kuali.common.threads.listener.MavenConsoleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadInvoker {
    private final Logger logger = LoggerFactory.getLogger(ThreadHandlerFactory.class);

    public <T> ExecutionStatistics invokeThreads(int max, int min, int divisor, ThreadHandlerContext<T> context) {
        if (context.getHandler() == null || context.getList() == null) {
            throw new IllegalArgumentException("Neither elementHandler nor list can be null");
        }
        ThreadHandlerFactory factory = new ThreadHandlerFactory();
        ThreadHandlerContext<T> thc = new ThreadHandlerContext<T>();
        thc.setMax(max);
        thc.setMin(min);
        thc.setDivisor(divisor);
        if (thc.getListener() == null) {
            thc.setListener(new MavenConsoleListener<T>());
        }
        ThreadHandler<T> handler = factory.getThreadHandler(thc);
        int elementsPerThread = handler.getElementsPerThread();
        int size = thc.getList().size();
        logger.info("Executing [t:" + handler.getThreadCount() + " e:" + elementsPerThread + " s:" + size + "]");
        handler.executeThreads();
        return handler.getExecutionStatistics();
    }

    public <T> ExecutionStatistics invokeThreads(int max, ElementHandler<T> elementHandler, List<T> list) {
        ThreadHandlerContext<T> thc = new ThreadHandlerContext<T>();
        thc.setMax(max);
        thc.setHandler(elementHandler);
        thc.setList(list);
        return invokeThreads(max, 0, 0, thc);
    }

}
