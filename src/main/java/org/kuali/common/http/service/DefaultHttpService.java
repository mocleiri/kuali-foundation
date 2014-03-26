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

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.currentTimeMillis;
import static org.kuali.common.http.model.HttpStatus.SUCCESS;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Threads.sleep;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.http.model.HttpStatus;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class DefaultHttpService implements HttpService {

	private static final Logger logger = newLogger();

	@Override
	public HttpWaitResult wait(String url) {
		return wait(HttpContext.create(url));
	}

	@Override
	public HttpWaitResult wait(HttpContext context) {
		HttpWaitResult result = getWaitResult(context);
		HttpStatus actual = result.getStatus();
		if (!context.isQuiet()) {
			checkState(SUCCESS.equals(result.getStatus()), "[%s] returned [%s]", context.getUrl(), actual);
		}
		return result;
	}

	protected HttpWaitResult getWaitResult(HttpContext context) {
		logger.debug(context.getUrl());
		CloseableHttpClient client = getHttpClient(context);
		long start = currentTimeMillis();
		long end = start + context.getOverallTimeoutMillis();
		List<HttpRequestResult> requestResults = newArrayList();
		Object[] args = { context.getLogMsgPrefix(), context.getUrl(), FormatUtils.getTime(context.getOverallTimeoutMillis()) };
		if (!context.isQuiet()) {
			logger.info("{} - [{}] - [Timeout in {}]", args);
		}
		int retryAttempts = 0;
		for (;;) {
			HttpRequestResult rr = doRequest(client, context);
			requestResults.add(rr);
			if (!isFinishState(context, rr, end, retryAttempts)) {
				logHttpRequestResult(context.getLogMsgPrefix(), rr, context.getUrl(), end, context.isQuiet());
				sleep(context.getSleepIntervalMillis());
			} else {
				HttpStatus status = getResultStatus(context, retryAttempts, rr, end);
				HttpWaitResult waitResult = HttpWaitResult.builder(status, rr, start).requestResults(requestResults).build();
				logWaitResult(waitResult, context.getUrl(), context.getLogMsgPrefix(), context.isQuiet());
				return waitResult;
			}
			retryAttempts++;
		}
	}

	protected void logHttpRequestResult(String logMsgPrefix, HttpRequestResult result, String url, long end, boolean quiet) {
		String statusText = getStatusText(result);
		String timeout = FormatUtils.getTime(end - currentTimeMillis());
		Object[] args = { logMsgPrefix, url, statusText, timeout };
		if (!quiet) {
			logger.info("{} - [{}] - [{}] - [Timeout in {}]", args);
		}
	}

	protected void logWaitResult(HttpWaitResult result, String url, String logMsgPrefix, boolean quiet) {
		String status = result.getStatus().toString();
		String elapsed = FormatUtils.getTime(result.getStop() - result.getStart());
		String statusText = getStatusText(result.getFinalRequestResult());
		Object[] args = { logMsgPrefix, url, status, statusText, elapsed };
		if (!quiet) {
			logger.info("{} - [{}] - [{} - {}]  Total time: {}", args);
		}
	}

	protected String getStatusText(HttpRequestResult result) {
		if (result.getException().isPresent()) {
			return result.getStatusText();
		} else {
			int code = result.getStatusCode().get();
			return code + " - " + result.getStatusText();
		}
	}

	protected HttpStatus getResultStatus(HttpContext context, int retryAttempts, HttpRequestResult rr, long end) {
		// Make sure we haven't exceeded the max number of retry attempts
		if (maxRetriesExceeded(context, retryAttempts)) {
			return HttpStatus.MAX_RETRIES_EXCEEDED;
		}

		// If we've gone past our max allotted time, we've timed out
		if (rr.getStop() > end) {
			return HttpStatus.TIMEOUT;
		}

		// Don't think we'll ever fall into this. Logic always continues re-trying until timeout is exceeded if an IO exception is returned
		if (rr.getException().isPresent()) {
			return HttpStatus.IO_EXCEPTION;
		}

		// If we have not timed out and there is no exception, we must have gotten a valid http response code back
		checkState(rr.getStatusCode().isPresent(), "statusCode should never be null here");

		// If there is a status code and it matches a success code, we are done
		if (isSuccess(context.getSuccessCodes(), rr.getStatusCode().get())) {
			return HttpStatus.SUCCESS;
		} else {
			return HttpStatus.INVALID_HTTP_STATUS_CODE;
		}
	}

	protected boolean maxRetriesExceeded(HttpContext context, int retryAttempts) {
		if (context.getMaxRetries().isPresent()) {
			return retryAttempts > context.getMaxRetries().get();
		} else {
			return false;
		}
	}

	protected boolean quitTrying(HttpContext context, int retryAttempts) {
		if (context.getMaxRetries().isPresent()) {
			return retryAttempts >= context.getMaxRetries().get();
		} else {
			return false;
		}
	}

	protected boolean isFinishState(HttpContext context, HttpRequestResult rr, long end, int retryAttempts) {
		// If we've gone past the number of max retries allowed, we are done
		if (quitTrying(context, retryAttempts)) {
			return true;
		}

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

	protected HttpRequestResult doRequest(CloseableHttpClient client, HttpContext context) {
		long start = currentTimeMillis();
		try {
			HttpGet httpGet = new HttpGet(context.getUrl());
			CloseableHttpResponse response = client.execute(httpGet);
			Optional<String> responseBody = getResponseBodyAsString(response, context);
			int statusCode = response.getStatusLine().getStatusCode();
			String statusText = response.getStatusLine().getReasonPhrase();
			return HttpRequestResult.builder(statusText, statusCode, responseBody, start).build();
		} catch (IOException e) {
			return HttpRequestResult.builder(e, start).build();
		}
	}

	protected Optional<String> getResponseBodyAsString(CloseableHttpResponse response, HttpContext context) {
		InputStream in = null;
		try {
			Optional<HttpEntity> entity = fromNullable(response.getEntity());
			if (!entity.isPresent()) {
				return absent();
			}
			in = entity.get().getContent();
			byte[] buffer = new byte[4096];
			int length = in.read(buffer);
			long bytesRead = 0;
			StringBuilder sb = new StringBuilder();
			while (length != -1) {
				String content = new String(buffer, 0, length, context.getEncoding());
				sb.append(content);
				bytesRead += length;
				if (isMaxBytes(bytesRead, context)) {
					break;
				}
				length = in.read(buffer);
			}
			return Optional.of(sb.toString());
		} catch (IOException e) {
			throw illegalState("unexpected io error", e);
		} finally {
			IOUtils.closeQuietly(response);
			IOUtils.closeQuietly(in);
		}
	}

	protected boolean isMaxBytes(long bytesRead, HttpContext context) {
		if (context.getMaxBytes().isPresent()) {
			long max = context.getMaxBytes().get();
			return bytesRead >= max;
		} else {
			return false;
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

	protected CloseableHttpClient getHttpClient(HttpContext context) {
		int timeout = context.getRequestTimeoutMillis();
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
		HttpRequestRetryHandler retryHandler = new StandardHttpRequestRetryHandler(0, false);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setStaleConnectionCheckEnabled(true).build();
		return HttpClients.custom().setRetryHandler(retryHandler).setDefaultSocketConfig(socketConfig).setDefaultRequestConfig(requestConfig).build();
	}

}