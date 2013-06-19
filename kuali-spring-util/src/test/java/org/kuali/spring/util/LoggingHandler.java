package org.kuali.spring.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoggingHandler implements InvocationHandler {

    protected Object delegate;

    public LoggingHandler(Object delegate) {
        this.delegate = delegate;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = method.invoke(delegate, args);
            return result;
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } finally {
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Method invocation took " + elapsed + " ms");
        }

    }

}
