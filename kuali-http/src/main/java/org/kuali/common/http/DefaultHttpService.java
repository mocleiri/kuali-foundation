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

public class DefaultHttpService implements HttpService {

	private final Logger logger = LoggerFactory.getLogger(DefaultHttpService.class);

	protected boolean isSuccess(int resultCode) {
		return isMatch(resultCode, successCodes);
	}

	protected boolean isContinueWaiting(int resultCode) {
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

	@Override
	public WaitResult wait(HttpContext context) {
		return new WaitResult();
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
			boolean continueWaiting = isContinueWaiting(statusCode);

			message = message.append(statusCode + ":" + statusText + "'");
			if (success) {
				// Everything is OK
				logger.info(getMsg(message.toString()));
				return Result.SUCCESS;
			} else if (continueWaiting) {
				// We got an HTTP status code that does not represent success,
				// but we should continue waiting
				// This can happen when Tomcat is fronted by an Apache web server
				// That configuration returns 503 if Tomcat isn't up and running yet
				logger.info(getMsg(message.toString()));
				return Result.CONTINUE_WAITING_HTTP_STATUS_CODE;
			} else {
				// We got an HTTP status code that we don't recognize, we are done
				logger.info(getMsg(message.toString(), secondsRemaining));
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
}
