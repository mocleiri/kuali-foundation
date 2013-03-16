/**
 * Copyright 2010-2012 The Kuali Foundation
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

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

public class EncUtils {

	private static final String DEFAULT_PASSWORD = "password";

	/**
	 * Return a <code>BasicTextEncryptor</code> who's password is <code>password</code>.
	 */
	public static final TextEncryptor getTextEncryptor() {
		return getTextEncryptor(EncryptionStrength.BASIC, DEFAULT_PASSWORD);
	}

	/**
	 * Return a <code>BasicTextEncryptor</code>
	 */
	public static final TextEncryptor getTextEncryptor(String password) {
		return getTextEncryptor(EncryptionStrength.BASIC, password);
	}

	/**
	 * Return a <code>BasicTextEncryptor</code> or <code>StrongTextEncryptor</code> depending on what <code>strength</code> is set to
	 */
	public static final TextEncryptor getTextEncryptor(EncryptionStrength strength, String password) {
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
			throw new IllegalArgumentException("Encryption strength '" + strength + "' is unknown");
		}
	}
}
