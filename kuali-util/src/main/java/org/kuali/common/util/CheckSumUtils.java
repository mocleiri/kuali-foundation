/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckSumUtils {

	private static final String MD5 = "MD5";
	private static final String SHA1 = "SHA1";
	private static final String UTF8 = "UTF-8";

	public static String getSHA1Checksum(String message) {
		return getChecksum(message, SHA1);
	}

	public static String getMD5Checksum(String message) {
		return getChecksum(message, MD5);
	}

	public static String getMD5Checksum(InputStream in) throws IOException {
		return getChecksum(in, MD5);
	}

	public static String getSHA1Checksum(InputStream in) throws IOException {
		return getChecksum(in, SHA1);
	}

	public static String getChecksum(String message, String algorithm) {
		try {
			return getChecksum(getInputStream(message), algorithm);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	public static String getChecksum(InputStream in, String algorithm) throws IOException {
		try {
			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance(algorithm);
			int numRead;
			do {
				numRead = in.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			byte[] bytes = complete.digest();
			return toHex(bytes);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Unexpected message digest error", e);
		} catch (IOException e) {
			throw e;
		}
	}

	protected static String toHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	protected static InputStream getInputStream(String message) {
		try {
			return getInputStream(message, UTF8);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	protected static InputStream getInputStream(String message, String encoding) throws UnsupportedEncodingException {
		Assert.noBlanks(message);
		byte[] bytes = message.getBytes(encoding);
		return new ByteArrayInputStream(bytes);
	}

}
