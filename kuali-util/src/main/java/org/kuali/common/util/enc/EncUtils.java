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

import static org.kuali.common.util.base.Exceptions.illegalArgument;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.enc.EncStrength.DEFAULT_ENCRYPTION_STRENGTH;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class EncUtils {

	private static final String MAGIC_PREFIX = "enc--"; // Handy for passing encrypted args via CLI. Parenthesis tend to confuse CLI shells
	private static final String PREFIX = "ENC("; // Jasypt prefers this for values in properties files
	private static final String SUFFIX = ")";

	public static final Optional<EncryptionService> ABSENT = Optional.absent();

	/**
	 * Return true if the text is enclosed with <code>ENC()</code> or starts with <code>enc--</code>
	 */
	public static boolean isEncrypted(String text) {
		if (text == null) {
			return false;
		}
		return Str.matches(text, PREFIX, SUFFIX) || text.startsWith(MAGIC_PREFIX);
	}

	public static String unwrap(String text) {
		Assert.noBlanks(text);
		Assert.encrypted(text);
		if (text.startsWith(MAGIC_PREFIX)) {
			return Str.removePrefix(text, MAGIC_PREFIX);
		} else {
			return Str.remove(text, PREFIX, SUFFIX);
		}
	}

	public static String wrap(String text) {
		Assert.noBlanks(text);
		Assert.notEncrypted(text);
		return MAGIC_PREFIX + text;
	}

	/**
	 * Returns a <code>BasicTextEncryptor</code> that uses <code>password</code> to encrypt/decrypt.
	 */
	public static TextEncryptor getTextEncryptor(String password) {
		return getTextEncryptor(password, DEFAULT_ENCRYPTION_STRENGTH);
	}

	/**
	 * Return a <code>BasicTextEncryptor</code> or <code>StrongTextEncryptor</code> depending on what <code>strength</code> is set to
	 */
	public static TextEncryptor getTextEncryptor(String password, EncStrength strength) {
		checkNotBlank(password, "password");
		checkNotNull(strength, "strength");
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
			throw illegalArgument("encryption strength [%s] is unknown", strength);
		}
	}

	/**
	 * If enc and string are both present and the string is encrypted, return the decrypted string. Otherwise do nothing.
	 */
	public static Optional<String> decrypt(Optional<EncryptionService> enc, Optional<String> string) {
		if (enc.isPresent() && string.isPresent()) {
			return Optional.of(enc.get().decrypt(string.get()));
		} else {
			return string;
		}
	}

	/**
	 * If enc is present and the string is encrypted, return the decrypted string. Otherwise do nothing.
	 */
	public static String decrypt(Optional<EncryptionService> enc, String string) {
		return decrypt(enc, Optional.of(string)).get();
	}

	/**
	 * If enc is present, return a new list containing the same elements in the same order only with any encrypted strings having been decrypted.
	 */
	public static List<String> decrypt(Optional<EncryptionService> enc, List<String> strings) {
		if (!enc.isPresent()) {
			return strings;
		}
		List<String> decrypted = new ArrayList<String>();
		for (String string : strings) {
			decrypted.add(enc.get().decrypt(string));
		}
		return ImmutableList.copyOf(decrypted);
	}

	/**
	 * Return a new list containing the same elements in the same order only with any encrypted strings having been decrypted.
	 */
	public static List<String> decrypt(EncryptionService enc, List<String> strings) {
		return decrypt(Optional.of(enc), strings);
	}

	public static Optional<String> decrypt(EncryptionService enc, Optional<String> optional) {
		return decrypt(Optional.of(enc), optional);
	}

}
