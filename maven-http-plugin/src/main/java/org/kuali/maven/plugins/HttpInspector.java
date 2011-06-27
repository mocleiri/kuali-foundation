package org.kuali.maven.plugins;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private final Logger logger = LoggerFactory.getLogger(HttpInspector.class);
	String dateFormat = "yyyy-MM-dd HH:mm:ss z";
	List<Integer> successCodes = new ArrayList<Integer>();
	int requestTimeout = 3000;
	int sleepInterval = 3000;
	int timeout = 180;

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	protected boolean isSuccess(int resultCode) {
		for (int successCode : successCodes) {
			if (resultCode == successCode) {
				return true;
			}
		}
		return false;
	}

	protected String getMsg(String msg, SimpleDateFormat sdf) {
		return getMsg(msg, -1, sdf);
	}

	protected String getMsg(String msg, long l, SimpleDateFormat sdf) {
		StringBuilder sb = new StringBuilder();
		if (l != -1) {
			sb.append(l + " - ");
		}
		sb.append(msg);
		sb.append(" - ");
		sb.append(sdf.format(new Date()));
		return sb.toString();
	}

	public boolean wait(String url) {
		HttpClient client = getHttpClient();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		long now = System.currentTimeMillis();
		long end = now + (timeout * 1000);
		logger.info(getMsg("Determining status for '" + url + "'", sdf));
		for (;;) {
			long secondsRemaining = (long) Math.ceil((end - System.currentTimeMillis()) / 1000D);
			boolean success = doRequest(client, url, secondsRemaining, sdf);
			if (success) {
				return true;
			}
			sleep(sleepInterval);
			if (System.currentTimeMillis() > end) {
				logger.info("Timed out waiting for response from '" + url + "'");
				return false;
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

	protected boolean doRequest(HttpClient client, String url, long secondsRemaining, SimpleDateFormat sdf) {
		try {
			HttpMethod method = new GetMethod(url);
			client.executeMethod(method);
			int statusCode = method.getStatusCode();
			String statusText = method.getStatusText();
			boolean success = isSuccess(statusCode);
			if (success) {
				logger.info(getMsg("Status for '" + url + "' is '" + statusCode + ":" + statusText + "'", sdf));
				return true;
			} else {
				logger.info(getMsg("Status for '" + url + "' is '" + statusCode + ":" + statusText + "'",
						secondsRemaining, sdf));
			}
		} catch (IOException e) {
			logger.info(getMsg("Status for '" + url + "' is '" + e.getMessage() + "'", secondsRemaining, sdf));
		}
		return false;
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

	public String getDateFormat() {
		return dateFormat;
	}

	public List<Integer> getSuccessCodes() {
		return successCodes;
	}

	public void setSuccessCodes(List<Integer> successCodes) {
		this.successCodes = successCodes;
	}
}
