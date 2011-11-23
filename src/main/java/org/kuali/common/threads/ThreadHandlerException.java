package org.kuali.common.threads;

public class ThreadHandlerException extends RuntimeException {

    private static final long serialVersionUID = -9141511246502324732L;

    public ThreadHandlerException() {
    }

    public ThreadHandlerException(String message) {
        super(message);
    }

    public ThreadHandlerException(Throwable cause) {
        super(cause);
    }

    public ThreadHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

}
