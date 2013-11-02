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
package org.kuali.common.dns.http;

import org.apache.commons.httpclient.Header;

public class HttpRequestResult {

	int statusCode;
	String statusText;
	HttpRequestResultType type;
	Exception exception;
	Header[] responseHeaders;
	String responseBody;

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public HttpRequestResultType getType() {
		return type;
	}

	public void setType(HttpRequestResultType type) {
		this.type = type;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Header[] getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Header[] responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
}
