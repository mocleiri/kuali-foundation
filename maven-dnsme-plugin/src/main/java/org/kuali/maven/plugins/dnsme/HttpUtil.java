package org.kuali.maven.plugins.dnsme;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
    private final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    int requestTimeout = 8000;
    int sleepInterval = 3000;
    int timeout = 300;

    protected String getMsg(String msg) {
        return getMsg(msg, -1);
    }

    protected String getMsg(String msg, long l) {
        StringBuilder sb = new StringBuilder();
        sb.append(msg);
        if (l == -1) {
            return sb.toString();
        }
        sb.append(" - (Timeout in " + l + "s)");
        return sb.toString();
    }

    public void log(String url, HttpRequestResult result, int secondsRemaining) {
        StringBuilder sb = new StringBuilder();
        sb.append("Status for '" + url + "' is '");
        switch (result.getType()) {
        case EXCEPTION:
            Exception exception = result.getException();
            sb.append(exception.getMessage());
            break;
        case COMPLETED:
            int statusCode = result.getStatusCode();
            String statusText = result.getStatusText();
            String msg = statusCode + ":" + statusText;
            sb.append(msg);
            break;
        case TIMEOUT:
            sb.append("Timeout exceeded");
            break;
        default:
            throw new IllegalArgumentException(result.getType() + " is an unknown type");
        }
        sb.append("'");
        logger.info(getMsg(sb.toString(), secondsRemaining));
    }

    protected int getSecondsRemaining(long endMillis) {
        long currentMillis = System.currentTimeMillis();
        long millisRemaining = endMillis - currentMillis;
        double secondsRemaining = millisRemaining / 1000D;
        return (int) Math.ceil(secondsRemaining);
    }

    public HttpRequestResult doWait(String url) {
        HttpClient client = getHttpClient();
        long now = System.currentTimeMillis();
        long end = now + (timeout * 1000);
        logger.info(getMsg("Determining status for '" + url + "'"));
        for (;;) {
            HttpRequestResult result = getResult(client, url);
            int secondsRemaining = getSecondsRemaining(end);
            log(url, result, secondsRemaining);
            if (ResultType.COMPLETED.equals(result.getType())) {
                return result;
            }
            sleep(sleepInterval);
            if (System.currentTimeMillis() > end) {
                result.setType(ResultType.TIMEOUT);
                log(url, result, -1);
                return result;
            }
        }
    }

    protected HttpClient getHttpClient() {
        HttpClient client = new HttpClient();
        HttpClientParams clientParams = client.getParams();
        HttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(0, false);
        clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);
        clientParams.setParameter(HttpMethodParams.SO_TIMEOUT, requestTimeout);
        return client;
    }

    protected String getResponseBody(HttpMethod method) throws IOException {
        InputStream in = null;
        try {
            in = method.getResponseBodyAsStream();
            return IOUtils.toString(in);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public HttpRequestResult getResult(HttpClient client, HttpMethod method) {
        HttpRequestResult result = new HttpRequestResult();
        try {
            client.executeMethod(method);
            int statusCode = method.getStatusCode();
            String statusText = method.getStatusText();
            String responseBody = getResponseBody(method);
            method.releaseConnection();
            result.setStatusCode(statusCode);
            result.setStatusText(statusText);
            result.setResponseBody(responseBody);
            result.setType(ResultType.COMPLETED);
        } catch (Exception e) {
            result.setType(ResultType.EXCEPTION);
            result.setException(e);
        }
        return result;
    }

    public HttpRequestResult getResult(HttpClient client, String url) {
        HttpMethod method = new GetMethod(url);
        return getResult(client, method);
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(int sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int waitTimeout) {
        this.timeout = waitTimeout;
    }

    public Logger getLogger() {
        return logger;
    }
}
