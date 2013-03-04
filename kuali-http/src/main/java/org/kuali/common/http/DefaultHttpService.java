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
package org.kuali.common.http;

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
import org.kuali.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHttpService implements HttpService {

	private final Logger logger = LoggerFactory.getLogger(DefaultHttpService.class);

	@Override
	public HttpWaitResult wait(HttpContext context) {
		Assert.notBlank(context.getUrl(), "url is blank");
		logger.debug(context.getUrl());
		HttpClient client = getHttpClient(context);
		HttpWaitResult waitResult = new HttpWaitResult();
		waitResult.setStart(System.currentTimeMillis());
		long end = waitResult.getStart() + context.getOverallTimeoutMillis();
		List<HttpRequestResult> requestResults = new ArrayList<HttpRequestResult>();
		waitResult.setRequestResults(requestResults);
		logger.info("Determining status for {} - (Timeout in {})", context.getUrl(), end - waitResult.getStart());
		for (;;) {
			HttpRequestResult rr = doRequest(client, context);
			requestResults.add(rr);
			if (!isFinishState(context, rr, end)) {
				sleep(context.getSleepIntervalMillis());
			} else {
				return waitResult;
			}
		}
	}

	protected boolean isFinishState(HttpContext context, HttpRequestResult rr, long end) {
		// If we've gone past our max allotted time, we are done
		if (System.currentTimeMillis() > end) {
			return true;
		}

		// If there is no status code at all, we need to keep trying
		Integer statusCode = rr.getStatusCode();
		if (statusCode == null) {
			return false;
		}

		// If there is a status code and it matches a success code, we are done
		if (isSuccess(context.getSuccessCodes(), statusCode)) {
			return true;
		}

		// If there is a status code and it matches a continue waiting code, we need to keep trying
		if (isContinueWaiting(context.getContinueWaitingCodes(), statusCode)) {
			return false;
		} else {
			// We got an http status code, but it wasn't we were expecting. We need to fail
			return true;
		}
	}

	protected HttpRequestResult doRequest(HttpClient client, HttpContext context) {
		HttpRequestResult result = new HttpRequestResult();
		result.setStart(System.currentTimeMillis());
		try {
			HttpMethod method = new GetMethod(context.getUrl());
			client.executeMethod(method);
			result.setStatusCode(method.getStatusCode());
			result.setStatusText(method.getStatusText());
		} catch (IOException e) {
			result.setException(e);
		}
		result.setStop(System.currentTimeMillis());
		result.setElapsed(result.getStop() - result.getStart());
		return result;
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

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
