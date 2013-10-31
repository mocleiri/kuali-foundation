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
package org.kuali.common.util.enc;

import org.codehaus.plexus.util.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;

public class EncUtils {

	private static final String ENCRYPTION_PREFIX = "ENC(";
	private static final String ENCRYPTION_SUFFIX = ")";

	public static boolean isEncrypted(String text) {
		Assert.noBlanks(text);
		return text.startsWith(ENCRYPTION_PREFIX) && text.endsWith(ENCRYPTION_SUFFIX);
	}

	public static String unwrap(String wrappedText) {
		Assert.noBlanks(wrappedText);
		Assert.isTrue(isEncrypted(wrappedText), "Text is not wrapped");
		return StringUtils.substring(wrappedText, ENCRYPTION_PREFIX.length(), ENCRYPTION_SUFFIX.length());
	}

	public static String wrap(String encryptedText) {
		Assert.noBlanks(encryptedText);
		Assert.isFalse(isEncrypted(encryptedText), "Text is already wrapped");
		return ENCRYPTION_PREFIX + encryptedText + ENCRYPTION_SUFFIX;
	}

	/**
	 * Returns a <code>BasicTextEncryptor</code> that uses <code>password</code> to encrypt/decrypt.
	 */
	public static TextEncryptor getTextEncryptor(String password) {
		return getTextEncryptor(password, EncStrength.DEFAULT_VALUE);
	}

	/**
	 * Return a <code>BasicTextEncryptor</code> or <code>StrongTextEncryptor</code> depending on what <code>strength</code> is set to
	 */
	public static TextEncryptor getTextEncryptor(String password, EncStrength strength) {
		Assert.noBlanks(password);
		switch (strength) {
		case BASIC:
			BasicTextEncryptor basic = new BasicTextEncryptor();
			basic.setPassword(password);
			return basic;
		case STRONG:
			StrongTextEncryptor strong = new StrongTextEncryptor();
			strong.setPassword(password);
			return strong;
		default:
			throw new IllegalArgumentException("Encryption strength [" + strength + "] is unknown");
		}
	}
}
