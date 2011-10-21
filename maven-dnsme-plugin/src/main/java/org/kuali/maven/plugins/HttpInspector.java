package org.kuali.maven.plugins;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInspector {
    private final Logger logger = LoggerFactory.getLogger(HttpInspector.class);
    List<Integer> successCodes = new ArrayList<Integer>();
    int requestTimeout = 3000;
    int sleepInterval = 3000;
    int timeout = 180;

    protected boolean isSuccess(int resultCode) {
        for (int successCode : successCodes) {
            if (resultCode == successCode) {
                return true;
            }
        }
        return false;
    }

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

    public Result wait(String url) {
        HttpClient client = getHttpClient();
        long now = System.currentTimeMillis();
        long end = now + (timeout * 1000);
        logger.info(getMsg("Determining status for '" + url + "'"));
        for (;;) {
            long secondsRemaining = (long) Math.ceil((end - System.currentTimeMillis()) / 1000D);
            Result result = doRequest(client, url, secondsRemaining);
            if (result.equals(Result.SUCCESS)) {
                return result;
            } else if (result.equals(Result.INVALID_HTTP_STATUS_CODE)) {
                logger.info("Invalid http status code.  Expected " + successCodes);
                return result;
            }
            sleep(sleepInterval);
            if (System.currentTimeMillis() > end) {
                logger.info("Timed out waiting for response from '" + url + "'");
                return Result.TIMEOUT;
            }
        }
    }

    public HttpClient getHttpClient() {
        HttpClient client = new HttpClient();
        HttpClientParams clientParams = client.getParams();
        HttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(0, false);
        clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);
        clientParams.setParameter(HttpMethodParams.SO_TIMEOUT, requestTimeout);
        return client;
    }

    public Result doDNSMERequest(HttpClient client) {
        try {
            Config config = new SandboxConfig();
            Api api = new Api(config);
            String apiKey = config.getApiKey();
            String requestDate = api.getHTTPDate(new Date());
            String hash = api.getHMACSHA1Hash(requestDate);
            String url = config.getBaseUrl();
            HttpMethod method = new GetMethod(url);
            Header header1 = new Header("x-dnsme-apiKey", apiKey);
            Header header2 = new Header("x-dnsme-requestDate", requestDate);
            Header header3 = new Header("x-dnsme-hmac", hash);
            method.addRequestHeader(header1);
            method.addRequestHeader(header2);
            method.addRequestHeader(header3);

            client.executeMethod(method);
            int statusCode = method.getStatusCode();
            String statusText = method.getStatusText();
            InputStream in = method.getResponseBodyAsStream();
            String s = getString(in);
            in.close();
            method.releaseConnection();
            System.out.println("Status: '" + statusCode + ":" + statusText + "'");
            System.out.println("Response:");
            System.out.println(s);
            return Result.SUCCESS;
        } catch (IOException e) {
            return Result.IO_EXCEPTION;
        }
    }

    protected String getString(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, bytesRead));
        }
        return sb.toString();

    }

    protected Result doRequest(HttpClient client, String url, long secondsRemaining) {
        try {
            HttpMethod method = new GetMethod(url);
            client.executeMethod(method);
            int statusCode = method.getStatusCode();
            String statusText = method.getStatusText();
            boolean success = isSuccess(statusCode);
            if (success) {
                logger.info(getMsg("Status for '" + url + "' is '" + statusCode + ":" + statusText + "'"));
                return Result.SUCCESS;
            } else {
                logger.info(getMsg("Status for '" + url + "' is '" + statusCode + ":" + statusText + "'",
                        secondsRemaining));
                return Result.INVALID_HTTP_STATUS_CODE;
            }
        } catch (IOException e) {
            logger.info(getMsg("Status for '" + url + "' is '" + e.getMessage() + "'", secondsRemaining));
            return Result.IO_EXCEPTION;
        }
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

    public List<Integer> getSuccessCodes() {
        return successCodes;
    }

    public void setSuccessCodes(List<Integer> successCodes) {
        this.successCodes = successCodes;
    }
}
