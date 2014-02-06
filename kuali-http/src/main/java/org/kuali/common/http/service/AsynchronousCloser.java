package org.kuali.common.http.service;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import java.io.InputStream;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.io.IOUtils;

public final class AsynchronousCloser implements Runnable {

	public AsynchronousCloser(HttpMethod method, InputStream in) {
		this.method = assertNotNull(method, "method");
		this.in = assertNotNull(in, "in");
	}

	private final HttpMethod method;
	private final InputStream in;

	@Override
	public void run() {
		method.releaseConnection();
		IOUtils.closeQuietly(in);
	}

}
