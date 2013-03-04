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
import org.kuali.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHttpService implements HttpService {

	private final Logger logger = LoggerFactory.getLogger(DefaultHttpService.class);

	@Override
	public WaitResult wait(HttpContext context) {
		Assert.notBlank(context.getUrl(), "url is blank");
		String url = context.getUrl();
		HttpClient client = getHttpClient(context);
		long now = System.currentTimeMillis();
		long end = now + (context.getOverallTimeout() * 1000);
		WaitResult wr = new WaitResult();
		wr.setStart(now);
		logger.info(getMsg("Determining status for '" + context.getUrl() + "'"));
		for (;;) {
			long secondsRemaining = (long) Math.ceil((end - System.currentTimeMillis()) / 1000D);
			RequestResultEnum result = doRequest(client, context, secondsRemaining);
			if (result.equals(RequestResultEnum.SUCCESS)) {
				wr.setRequestResult(result);
				wr.setStart(System.currentTimeMillis());
				wr.setElapsed(wr.getStop() - now);
				return wr;
			} else if (result.equals(RequestResultEnum.INVALID_HTTP_STATUS_CODE)) {
				logger.info("Invalid http status code.  Expected " + context.getSuccessCodes());
				wr.setRequestResult(result);
				wr.setStart(System.currentTimeMillis());
				wr.setElapsed(wr.getStop() - now);
				return wr;
			}
			sleep(context.getRequestTimeout());
			if (System.currentTimeMillis() > end) {
				logger.info("Timed out waiting for response from '" + url + "'");
				wr.setRequestResult(result);
				wr.setStart(System.currentTimeMillis());
				wr.setElapsed(wr.getStop() - now);
				return wr;
			}
		}
	}

	protected RequestResultEnum doRequest(HttpClient client, HttpContext context, long secondsRemaining) {
		String url = context.getUrl();
		StringBuilder message = new StringBuilder("Status for '" + url + "' is '");
		try {
			HttpMethod method = new GetMethod(url);
			client.executeMethod(method);
			int statusCode = method.getStatusCode();
			String statusText = method.getStatusText();
			boolean success = isSuccess(context.getSuccessCodes(), statusCode);
			boolean continueWaiting = isContinueWaiting(context.getContinueWaitingCodes(), statusCode);

			message = message.append(statusCode + ":" + statusText + "'");
			if (success) {
				// Everything is OK
				logger.info(getMsg(message.toString()));
				return RequestResultEnum.SUCCESS;
			} else if (continueWaiting) {
				// We got an HTTP status code that does not represent success,
				// but we should continue waiting
				// This can happen when Tomcat is fronted by an Apache web server
				// That configuration returns 503 if Tomcat isn't up and running yet
				logger.info(getMsg(message.toString()));
				return RequestResultEnum.CONTINUE_WAITING_HTTP_STATUS_CODE;
			} else {
				// We got an HTTP status code that we don't recognize, we are done
				logger.info(getMsg(message.toString(), secondsRemaining));
				return RequestResultEnum.INVALID_HTTP_STATUS_CODE;
			}
		} catch (IOException e) {
			logger.info(getMsg(message.append(e.getMessage() + "'").toString(), secondsRemaining));
			return RequestResultEnum.IO_EXCEPTION;
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
		HttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(0, false);
		clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);
		clientParams.setParameter(HttpMethodParams.SO_TIMEOUT, context.getRequestTimeout());
		return client;
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
