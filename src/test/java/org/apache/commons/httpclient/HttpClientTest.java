package org.apache.commons.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void test1() {
		try {
			String url = "http://localhost:8070";
			GetMethod method = new GetMethod(url);
			HttpClient client = new HttpClient();
			HttpClientParams clientParams = client.getParams();
			HttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(0, false);
			clientParams.setParameter(HttpMethodParams.RETRY_HANDLER, retryHandler);

			int result = client.executeMethod(method);
			Header[] headers = method.getResponseHeaders();
			for (Header header : headers) {
				String name = header.getName();
				String value = header.getValue();
				System.out.println(name + "=" + value);
			}
			System.out.println(result);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
