package org.kuali.spring.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

public class CallbackContext {

    Object object;
    Method method;
    Object[] args;
    MethodProxy methodProxy;

    public CallbackContext() {
        this(null, null, null, null);
    }

    public CallbackContext(Object object, Method method, Object[] args, MethodProxy methodProxy) {
        super();
        this.object = object;
        this.method = method;
        this.args = args;
        this.methodProxy = methodProxy;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public void setMethodProxy(MethodProxy methodProxy) {
        this.methodProxy = methodProxy;
    }
}
