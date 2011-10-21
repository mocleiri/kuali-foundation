package org.kuali.maven.plugins;

import java.io.IOException;

public class HttpRequestResult {
    int statusCode;
    String statusText;
    String responseBody;
    ResultType type;
    IOException exception;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public ResultType getType() {
        return type;
    }

    public void setType(ResultType type) {
        this.type = type;
    }

    public IOException getException() {
        return exception;
    }

    public void setException(IOException exception) {
        this.exception = exception;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
