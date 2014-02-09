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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

public final class AsynchronousCloser implements Runnable {

	public AsynchronousCloser(CloseableHttpResponse response, InputStream in) {
		this.response = checkNotNull(response, "response");
		this.in = checkNotNull(in, "in");
	}

	private final CloseableHttpResponse response;
	private final InputStream in;

	@Override
	public void run() {
		IOUtils.closeQuietly(response);
		IOUtils.closeQuietly(in);
	}

}
