/**
 * Copyright 2004-2012 The Kuali Foundation
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
