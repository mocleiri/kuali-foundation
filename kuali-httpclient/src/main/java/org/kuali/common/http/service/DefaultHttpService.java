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
import static org.kuali.common.util.base.Exceptions.illegalState;

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
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.base.Threads;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class DefaultHttpService implements HttpService {

	private static final Logger logger = Loggers.make();

	@Override
	public HttpWaitResult wait(String url) {
		return wait(HttpContext.create(url));
	}

	@Override
	public HttpWaitResult wait(HttpContext context) {
		HttpWaitResult result = getWaitResult(context);
		HttpStatus actual = result.getStatus();
		if (!context.isQuiet()) {
			Assert.isTrue(HttpStatus.SUCCESS.equals(result.getStatus()), "[" + context.getUrl() + "] returned [" + actual + "].");
		}
		return result;
	}

	protected HttpWaitResult getWaitResult(HttpContext context) {
		logger.debug(context.getUrl());
		CloseableHttpClient client = getHttpClient(context);
		long start = System.currentTimeMillis();
		long end = start + context.getOverallTimeoutMillis();
		List<HttpRequestResult> requestResults = Lists.newArrayList();
		Object[] args = { context.getLogMsgPrefix(), context.getUrl(), FormatUtils.getTime(context.getOverallTimeoutMillis()) };
		if (!context.isQuiet()) {
			logger.info("{} - [{}] - [Timeout in {}]", args);
		}
		int count = 0;
		for (;;) {
			HttpRequestResult rr = doRequest(client, context);
			count++;
			requestResults.add(rr);
			if (!isFinishState(context, rr, end, count)) {
				logHttpRequestResult(context.getLogMsgPrefix(), rr, context.getUrl(), end, context.isQuiet());
				Threads.sleep(context.getSleepIntervalMillis());
			} else {
				HttpStatus status = getResultStatus(context, rr, end);
				HttpWaitResult waitResult = new HttpWaitResult.Builder(status, rr, start).requestResults(requestResults).build();
				logWaitResult(waitResult, context.getUrl(), context.getLogMsgPrefix(), context.isQuiet());
				return waitResult;
			}
		}
	}

	protected void logHttpRequestResult(String logMsgPrefix, HttpRequestResult result, String url, long end, boolean quiet) {
		String statusText = getStatusText(result);
		String timeout = FormatUtils.getTime(end - System.currentTimeMillis());
		Object[] args = { logMsgPrefix, url, statusText, timeout };
		if (quiet) {
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

	protected boolean isFinishState(HttpContext context, HttpRequestResult rr, long end, int count) {
		// See if they have set a max retry limit
		if (context.getMaxRetries().isPresent()) {
			// If we have hit or exceeded the max retry limit, we are done
			int maxRetries = context.getMaxRetries().get();
			if (count >= maxRetries) {
				return true;
			}
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
		long start = System.currentTimeMillis();
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
			if (context.isAsynchronousClose()) {
				Runnable runnable = new AsynchronousCloser(response, in);
				Thread thread = new Thread(runnable, "async http closer");
				thread.setDaemon(true);
				thread.start();
			} else {
				IOUtils.closeQuietly(response);
				IOUtils.closeQuietly(in);
			}
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