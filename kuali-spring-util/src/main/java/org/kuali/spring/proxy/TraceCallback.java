package org.kuali.spring.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class TraceCallback implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TraceCallback.class);
    private static final Object MUTEX = new Object();
    long counter = 0;
    private static final int LANGUAGE_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE
            | Modifier.ABSTRACT | Modifier.STATIC | Modifier.FINAL | Modifier.SYNCHRONIZED | Modifier.NATIVE;

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return intercept(new CallbackContext(object, method, args, methodProxy));
    }

    public Object intercept(CallbackContext context) throws Throwable {
        long interceptCount = -1;
        synchronized (MUTEX) {
            interceptCount = this.counter++;
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Method Invocation Id=" + interceptCount + " Invoking {} parameters={}",
                    getString(context.getMethod()), context.getArgs());
        }
        Object result = context.getMethodProxy().invokeSuper(context.getObject(), context.getArgs());
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Method Invocation Id={} Result=[{}]", interceptCount, result);
        }
        return result;
    }

    protected String getTypeName(Class<?> type) {
        if (type.isArray()) {
            try {
                Class<?> cl = type;
                int dimensions = 0;
                while (cl.isArray()) {
                    dimensions++;
                    cl = cl.getComponentType();
                }
                StringBuffer sb = new StringBuffer();
                sb.append(cl.getName());
                for (int i = 0; i < dimensions; i++) {
                    sb.append("[]");
                }
                return sb.toString();
            } catch (Throwable e) { /* FALLTHRU */
            }
        }
        return type.getSimpleName();
    }

    protected String getString(Method method) {
        try {
            StringBuffer sb = new StringBuffer();
            int mod = method.getModifiers() & LANGUAGE_MODIFIERS;
            if (mod != 0) {
                sb.append(Modifier.toString(mod) + " ");
            }
            sb.append(getTypeName(method.getReturnType()) + " ");
            sb.append(getTypeName(method.getDeclaringClass()) + ".");
            sb.append(method.getName() + "(");
            Class<?>[] params = method.getParameterTypes();
            for (int j = 0; j < params.length; j++) {
                sb.append(getTypeName(params[j]));
                if (j < (params.length - 1)) {
                    sb.append(",");
                }
            }
            sb.append(")");
            Class<?>[] exceptions = method.getExceptionTypes();
            if (exceptions.length > 0) {
                sb.append(" throws ");
                for (int k = 0; k < exceptions.length; k++) {
                    sb.append(exceptions[k].getName());
                    if (k < (exceptions.length - 1)) {
                        sb.append(",");
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "<" + e + ">";
        }
    }

}
