/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.ksb.security.soap;

import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.kuali.rice.core.security.credentials.Credentials;
import org.kuali.rice.core.security.credentials.CredentialsSource;
import org.kuali.rice.ksb.messaging.ServiceInfo;
import org.kuali.rice.ksb.security.credentials.UsernamePasswordCredentials;
import org.springframework.util.Assert;


/**
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * @since 0.9
 * 
 */
public class CredentialsOutHandler extends WSS4JOutInterceptor {

	private final CredentialsSource credentialsSource;

	private final ServiceInfo serviceInfo;

	public CredentialsOutHandler(final CredentialsSource credentialsSource,
			final ServiceInfo serviceInfo) {
		Assert.notNull(credentialsSource, "credentialsSource cannot be null.");
		Assert.notNull(serviceInfo, "serviceInfo cannot be null.");
		this.credentialsSource = credentialsSource;
		this.serviceInfo = serviceInfo;

		final Credentials credentials = this.credentialsSource
				.getCredentials(this.serviceInfo.getEndpointUrl());

		Assert.isTrue(credentials instanceof UsernamePasswordCredentials,
				"Credentials must be of type usernamepassword.");

		final UsernamePasswordCredentials c = (UsernamePasswordCredentials) credentials;
		setProperty(WSHandlerConstants.USER, c.getUsername());
	}

	public WSPasswordCallback getPassword(final String username,
			final int doAction, final String clsProp, final String refProp,
			final RequestData reqData) throws WSSecurityException {
		final UsernamePasswordCredentials c = (UsernamePasswordCredentials) this.credentialsSource
				.getCredentials(this.serviceInfo.getEndpointUrl());

		return new WSPasswordCallback(c.getUsername(), c.getPassword(), null,
				WSPasswordCallback.USERNAME_TOKEN);
	}
}

