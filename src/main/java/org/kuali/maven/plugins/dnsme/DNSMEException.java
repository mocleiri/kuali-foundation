package org.kuali.maven.plugins.dnsme;

public class DNSMEException extends RuntimeException {

    private static final long serialVersionUID = 1991386822732240500L;

    public DNSMEException() {
    }

    public DNSMEException(String message) {
        super(message);
    }

    public DNSMEException(Throwable cause) {
        super(cause);
    }

    public DNSMEException(String message, Throwable cause) {
        super(message, cause);
    }

}
