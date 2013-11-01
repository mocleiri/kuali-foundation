/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.dns.dnsme;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.kuali.common.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	private final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	int requestTimeout = 8000;
	int sleepInterval = 3000;
	int timeout = 300;

	protected String getTimeout(long l) {
		if (l == -1) {
			return "";
		} else {
			return " - (Timeout in " + l + "s)";
		}
	}

	public void log(String url, HttpRequestResult result, int secondsRemaining) {
		StringBuilder sb = new StringBuilder();
		sb.append("Status for '" + url + "' is '" + getMsg(result) + "'");
		sb.append(getTimeout(secondsRemaining));
		logger.info(sb.toString());
	}

	protected String getMsg(HttpRequestResult result) {
		switch (result.getType()) {
		case EXCEPTION:
			Exception exception = result.getException();
			return exception.getMessage();
		case COMPLETED:
			int statusCode = result.getStatusCode();
			String statusText = result.getStatusText();
			return statusCode + ":" + statusText;
		case TIMEOUT:
			return "Timeout exceeded";
		default:
			throw new IllegalArgumentException(result.getType() + " is an unknown type");
		}
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
		long timeoutMillis = timeout * 1000;
		long end = now + timeoutMillis;
		logger.info("Determining status for '" + url + "'");
		for (;;) {
			HttpRequestResult result = executeMethod(client, url);
			int secondsRemaining = getSecondsRemaining(end);
			log(url, result, secondsRemaining);
			if (HttpRequestResultType.COMPLETED.equals(result.getType())) {
				return result;
			}
			if (System.currentTimeMillis() > end) {
				result.setType(HttpRequestResultType.TIMEOUT);
				log(url, result, -1);
				return result;
			}
			ThreadUtils.sleep(sleepInterval);
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

	protected String getResponseBody(HttpMethod method) throws IOException {
		InputStream in = null;
		try {
			in = method.getResponseBodyAsStream();
			return IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public HttpRequestResult executeMethod(HttpMethod method) {
		return executeMethod(getHttpClient(), method);
	}

	public HttpRequestResult executeMethod(HttpClient client, HttpMethod method) {
		HttpRequestResult result = new HttpRequestResult();
		try {
			client.executeMethod(method);
			int statusCode = method.getStatusCode();
			String statusText = method.getStatusText();
			String responseBody = getResponseBody(method);
			Header[] responseHeaders = method.getResponseHeaders();
			method.releaseConnection();
			result.setStatusCode(statusCode);
			result.setStatusText(statusText);
			result.setResponseBody(responseBody);
			result.setResponseHeaders(responseHeaders);
			result.setType(HttpRequestResultType.COMPLETED);
		} catch (Exception e) {
			result.setType(HttpRequestResultType.EXCEPTION);
			result.setException(e);
		}
		return result;
	}

	public HttpRequestResult executeMethod(HttpClient client, String url) {
		HttpMethod method = new GetMethod(url);
		return executeMethod(client, method);
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
