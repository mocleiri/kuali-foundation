/**
 * Copyright 2004-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInspector {
    private final static int PROXY_STATUS = 503;
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

    protected HttpClient getHttpClient() {
        HttpClient client = new HttpClient();
        HttpClientParams clientParams = client.getParams();
        HttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(0, false);
        clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);
        clientParams.setParameter(HttpMethodParams.SO_TIMEOUT, requestTimeout);
        return client;
    }

    protected Result doRequest(HttpClient client, String url, long secondsRemaining) {
        StringBuilder message = new StringBuilder("Status for '" + url + "' is '");
        try {
            HttpMethod method = new GetMethod(url);
            client.executeMethod(method);
            int statusCode = method.getStatusCode();
            String statusText = method.getStatusText();
            boolean success = isSuccess(statusCode);

            message = message.append(statusCode + ":" + statusText + "'");
            if (success) {
                logger.info(getMsg(message.toString()));
                return Result.SUCCESS;
            } 
            if (statusCode == PROXY_STATUS) {
                throw new IOException();
            }
            else {
                logger.info(getMsg(message.toString(),secondsRemaining));
                return Result.INVALID_HTTP_STATUS_CODE;
            }
        } catch (IOException e) {
            logger.info(getMsg(message.append(e.getMessage() + "'").toString(), secondsRemaining));
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
