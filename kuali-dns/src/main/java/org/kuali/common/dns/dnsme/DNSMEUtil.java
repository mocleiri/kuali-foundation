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
package org.kuali.common.dns.dnsme;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.kuali.common.dns.dnsme.beans.Account;

public class DNSMEUtil {
	// Sat, 12 Feb 2011 20:59:04 GMT
	public static final String DEFAULT_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
	public static final String DEFAULT_TIME_ZONE = "GMT";
	public static final String DEFAULT_ALGORITHM = "HmacSHA1";
	public static final String API_KEY_HEADER = "x-dnsme-apiKey";
	public static final String DATE_HEADER = "x-dnsme-requestDate";
	public static final String HMAC_HEADER = "x-dnsme-hmac";
	public static final int ONE_MINUTE_DELAY = 1000 * 60;

	String format;
	String algorithm;
	SimpleDateFormat sdf;
	TimeZone timeZone;

	public DNSMEUtil() {
		super();
		setFormat(DEFAULT_DATE_FORMAT);
		setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
		setAlgorithm(DEFAULT_ALGORITHM);
	}

	public synchronized void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public synchronized void setFormat(String format) {
		this.format = format;
		sdf = new SimpleDateFormat(format);
	}

	public synchronized void setTimeZone(TimeZone timezone) {
		this.timeZone = timezone;
		sdf.setTimeZone(timezone);
	}

	public synchronized String getHTTPDate(Date date) {
		return sdf.format(date);
	}

	public synchronized String getHash(String key, String data) {
		try {
			Mac mac = Mac.getInstance(algorithm);
			SecretKey secretKey = new SecretKeySpec(key.getBytes(), algorithm);
			mac.init(secretKey);
			byte[] finalBytes = mac.doFinal(data.getBytes());
			return getHexString(finalBytes);
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException(e);
		}
	}

	public List<Header> getAuthenticationHeaders(Account account) {
		/**
		 * The geniuses at DNS Made Easy did not account for computer clocks that have drifted forward.
		 * 
		 * The DNSME timestamp tolerance logic has 2 rules:<br>
		 * 1 - All timestamp values must be less than their own internal clock<br>
		 * 2 - All timestamp values must be within 5-6 minutes of their own clock.<br>
		 * 
		 * This is pretty retarded since computer clocks outside of DNSME are just as likely to drift forwards as they are backwards.<br>
		 * 
		 * If the timestamp you supply them is ahead of their internal clocks (even by a few milliseconds) the request gets denied. So, to compensate for any potential forward
		 * clock drift we subtract one minute from the current timestamp of the machine we are on. This only works as long as the machine we are on is less than one minute ahead of
		 * the DNSME clocks.
		 * 
		 * It would have been much better of them to accept timestamps that are within 5 minutes (plus OR minus) of their internal clocks.
		 */
		long millis = System.currentTimeMillis() - ONE_MINUTE_DELAY;
		String requestDate = getHTTPDate(new Date(millis));
		String hash = getHash(account.getSecretKey(), requestDate);
		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header(API_KEY_HEADER, account.getApiKey()));
		headers.add(new Header(DATE_HEADER, requestDate));
		headers.add(new Header(HMAC_HEADER, hash));
		return headers;
	}

	public HttpMethod getDeleteMethod(Account account, String url) {
		HttpMethod method = new DeleteMethod(url);
		addAuthenticationHeaders(account, method);
		return method;
	}

	public void updateMethod(Account account, String json, EntityEnclosingMethod method) {
		try {
			RequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
			addAuthenticationHeaders(account, method);
			Header accept = new Header("accept", "application/json");
			Header contentType = new Header("content-type", "application/json");
			method.addRequestHeader(accept);
			method.addRequestHeader(contentType);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	public GetMethod getGetMethod(Account account, String url) {
		GetMethod method = new GetMethod(url);
		addAuthenticationHeaders(account, method);
		return method;
	}

	protected void addAuthenticationHeaders(Account account, HttpMethod method) {
		List<Header> headers = getAuthenticationHeaders(account);
		for (Header header : headers) {
			method.addRequestHeader(header);
		}
	}

	public String getHexString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			int intValue1 = b[i] & 0xff;
			int intValue2 = intValue1 + 0x100;
			String s = Integer.toString(intValue2, 16);
			String one = s.substring(1);
			sb.append(one);
		}
		return sb.toString();
	}

	public String getFormat() {
		return format;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public String getAlgorithm() {
		return algorithm;
	}

}
