package org.kuali.maven.plugins.jenkins.context;

public class ProcessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1784042884373514140L;

    public ProcessException() {
        super();
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(Throwable exception) {
        super(exception);
    }

    public ProcessException(String message, Throwable exception) {
        super(message, exception);
    }

}
