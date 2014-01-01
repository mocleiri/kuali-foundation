/**
 * Copyright 2013-2014 The Kuali Foundation
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
package org.kuali.common.http.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.http.model.HttpStatus;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHttpService implements HttpService {

	private final Logger logger = LoggerFactory.getLogger(DefaultHttpService.class);

	@Override
	public HttpWaitResult wait(HttpContext context) {
		HttpWaitResult result = getWaitResult(context);
		HttpStatus actual = result.getStatus();
		Assert.isTrue(HttpStatus.SUCCESS.equals(result.getStatus()), "[" + context.getUrl() + "] returned [" + actual + "].");
		return result;
	}

	protected HttpWaitResult getWaitResult(HttpContext context) {
		logger.debug(context.getUrl());
		HttpClient client = getHttpClient(context);
		long start = System.currentTimeMillis();
		long end = start + context.getOverallTimeoutMillis();
		List<HttpRequestResult> requestResults = new ArrayList<HttpRequestResult>();
		Object[] args = { context.getLogMsgPrefix(), context.getUrl(), FormatUtils.getTime(context.getOverallTimeoutMillis()) };
		logger.info("{} - [{}] - [Timeout in {}]", args);
		for (;;) {
			HttpRequestResult rr = doRequest(client, context);
			requestResults.add(rr);
			if (!isFinishState(context, rr, end)) {
				logHttpRequestResult(context.getLogMsgPrefix(), rr, context.getUrl(), end);
				ThreadUtils.sleep(context.getSleepIntervalMillis());
			} else {
				HttpStatus status = getResultStatus(context, rr, end);
				HttpWaitResult waitResult = new HttpWaitResult.Builder(status, rr, start).requestResults(requestResults).build();
				logWaitResult(waitResult, context.getUrl(), context.getLogMsgPrefix());
				return waitResult;
			}
		}
	}

	protected void logHttpRequestResult(String logMsgPrefix, HttpRequestResult result, String url, long end) {
		String statusText = getStatusText(result);
		String timeout = FormatUtils.getTime(end - System.currentTimeMillis());
		Object[] args = { logMsgPrefix, url, statusText, timeout };
		logger.info("{} - [{}] - [{}] - [Timeout in {}]", args);
	}

	protected void logWaitResult(HttpWaitResult result, String url, String logMsgPrefix) {
		String status = result.getStatus().toString();
		String elapsed = FormatUtils.getTime(result.getStop() - result.getStart());
		String statusText = getStatusText(result.getFinalRequestResult());
		Object[] args = { logMsgPrefix, url, status, statusText, elapsed };
		logger.info("{} - [{}] - [{} - {}]  Total time: {}", args);
	}

	protected String getStatusText(HttpRequestResult result) {
		if (result.getException().isPresent()) {
			return result.getStatusText();
		} else {
			int code = result.getStatusCode().get();
			return code + " - " + result.getStatusText();
		}
	}

	protected HttpStatus getResultStatus(HttpContext context, HttpRequestResult rr, long end) {
		// If we've gone past our max allotted time, we've timed out
		if (rr.getStop() > end) {
			return HttpStatus.TIMEOUT;
		}

		// Don't think we'll ever fall into this. Logic always continues re-trying until timeout is exceeded if an IO exception is returned
		if (rr.getException().isPresent()) {
			return HttpStatus.IO_EXCEPTION;
		}

		// If we have not timed out and there is no exception, we must have gotten a valid http response code back
		Assert.isTrue(rr.getStatusCode().isPresent(), "statusCode should never be null here");

		// If there is a status code and it matches a success code, we are done
		if (isSuccess(context.getSuccessCodes(), rr.getStatusCode().get())) {
			return HttpStatus.SUCCESS;
		} else {
			return HttpStatus.INVALID_HTTP_STATUS_CODE;
		}
	}

	protected boolean isFinishState(HttpContext context, HttpRequestResult rr, long end) {
		// If we've gone past our max allotted time, we are done
		if (rr.getStop() > end) {
			return true;
		}

		// If there is no status code at all, we need to keep trying
		if (!rr.getStatusCode().isPresent()) {
			return false;
		}

		// If there is a status code and it matches a success code, we are done
		if (isSuccess(context.getSuccessCodes(), rr.getStatusCode().get())) {
			return true;
		}

		// If there is a status code and it matches a continue waiting code, we need to keep trying
		if (isContinueWaiting(context.getContinueWaitingCodes(), rr.getStatusCode().get())) {
			return false;
		} else {
			// We got an http status code, but it wasn't one we were expecting. We need to fail
			return true;
		}
	}

	protected HttpRequestResult doRequest(HttpClient client, HttpContext context) {
		long start = System.currentTimeMillis();
		try {
			HttpMethod method = new GetMethod(context.getUrl());
			client.executeMethod(method);
			String responseBody = getResponseBodyAsString(method, context.getEncoding());
			int statusCode = method.getStatusCode();
			String statusText = method.getStatusText();
			return new HttpRequestResult.Builder(statusText, statusCode, responseBody, start).build();
		} catch (IOException e) {
			return new HttpRequestResult.Builder(e, start).build();
		}
	}

	protected String getResponseBodyAsString(HttpMethod method, String encoding) {
		InputStream in = null;
		try {
			in = method.getResponseBodyAsStream();
			if (in == null) {
				return null;
			}
			return IOUtils.toString(in, encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			method.releaseConnection();
			IOUtils.closeQuietly(in);
		}
	}

	protected boolean isSuccess(List<Integer> successCodes, int resultCode) {
		return isMatch(resultCode, successCodes);
	}

	protected boolean isContinueWaiting(List<Integer> continueWaitingCodes, int resultCode) {
		return isMatch(resultCode, continueWaitingCodes);
	}

	protected boolean isMatch(int i, List<Integer> integers) {
		for (int integer : integers) {
			if (i == integer) {
				return true;
			}
		}
		return false;
	}

	protected HttpClient getHttpClient(HttpContext context) {
		HttpClient client = new HttpClient();
		HttpClientParams clientParams = client.getParams();

		// Disable the automatic retry capability built into the http client software
		// We will be handling retries on our own
		HttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(0, false);
		clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);

		// Set a hard timeout for this individual request so we get control back after a known amount of time
		clientParams.setParameter(HttpMethodParams.SO_TIMEOUT, context.getRequestTimeoutMillis());

		// Return the client configured with our preferences
		return client;
	}

}