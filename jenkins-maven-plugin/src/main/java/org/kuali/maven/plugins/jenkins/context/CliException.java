package org.kuali.maven.plugins.jenkins.context;

public class CliException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 3462717504491983361L;

    public CliException() {
        super();
    }

    public CliException(String message) {
        super(message);
    }

    public CliException(Throwable exception) {
        super(exception);
    }

    public CliException(String message, Throwable exception) {
        super(message, exception);
    }

}
