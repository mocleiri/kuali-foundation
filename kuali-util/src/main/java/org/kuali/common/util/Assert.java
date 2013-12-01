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

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.enc.EncUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public abstract class Assert extends org.springframework.util.Assert {

	private static final String NO_NULLS = "null not allowed";
	private static final String NO_BLANKS = "blank strings not allowed";

	/**
	 * Assert that <code>text</code> is concealed
	 */
	public static void concealed(String text) {
		isTrue(Str.isConcealed(text), "text must be concealed");
	}

	/**
	 * Assert that <code>text</code> is not concealed
	 */
	public static void notConcealed(String text) {
		isFalse(Str.isConcealed(text), "text is already concealed");
	}

	/**
	 * Assert that <code>text</code> is encrypted
	 */
	public static void encrypted(String text) {
		isTrue(EncUtils.isEncrypted(text), "text must be encrypted");
	}

	/**
	 * Assert that <code>text</code> is not encrypted
	 */
	public static void notEncrypted(String text) {
		isFalse(EncUtils.isEncrypted(text), "text is already encrypted");
	}

	/**
	 * Assert that <code>text</code> is not encrypted
	 * 
	 * @deprecated use notEncrypted instead
	 * @see notEncrypted
	 */
	@Deprecated
	public static void decrypted(String text) {
		notEncrypted(text);
	}

	/**
	 * Assert that none of the strings are encrypted
	 * 
	 * @deprecated use notEncrypted instead
	 * @see notEncrypted
	 */
	@Deprecated
	public static void decrypted(List<String> strings) {
		notEncrypted(strings);
	}

	/**
	 * Assert that none of the strings in the list are encrypted
	 */
	public static void notEncrypted(List<String> strings) {
		for (String string : strings) {
			notEncrypted(string);
		}
	}

	/**
	 * Assert that <code>port</code> is >= 0 and <= 65535
	 */
	public static void isPort(int port) {
		isTrue(port >= 0 && port <= 65535, "Port must be a number between 0 and 65535");
	}

	/**
	 * Assert that all of the numbers in the array are greater than or equal to zero
	 */
	public static void noNegatives(int... numbers) {
		for (int number : numbers) {
			notNegative(number);
		}
	}

	/**
	 * Assert that all of the numbers in the array are greater than or equal to zero
	 */
	public static void noNegatives(long... numbers) {
		for (long number : numbers) {
			notNegative(number);
		}
	}

	/**
	 * Assert that all of the numbers in the array are less than or equal to zero
	 */
	public static void noPositives(int... numbers) {
		for (int number : numbers) {
			notPositive(number);
		}
	}

	/**
	 * Assert that <code>i</code> is greater than or equal to zero
	 */
	public static void notNegative(int i) {
		isTrue(i >= 0, i + " is negative");
	}

	/**
	 * Assert that <code>i</code> is greater than or equal to zero
	 */
	public static void notNegative(long i) {
		isTrue(i >= 0, i + " is negative");
	}

	/**
	 * Assert that <code>i</code> is less than or equal to zero
	 */
	public static void notPositive(int i) {
		isTrue(i <= 0, i + " is positive");
	}

	/**
	 * Assert that <code>i</code> is greater than zero
	 */
	public static void positive(int i) {
		isTrue(i > 0, i + " is not a positive integer");
	}

	/**
	 * Assert that <code>i</code> is greater than zero
	 */
	public static void positive(long i) {
		isTrue(i > 0, i + " is not a positive long");
	}

	/**
	 * Assert that <code>i</code> is less than zero
	 */
	public static void negative(int i) {
		isTrue(i < 0, i + " is not a negative integer");
	}

	/**
	 * Assert that <code>i</code> is zero
	 */
	public static void zero(int i) {
		isTrue(i == 0, i + " is not zero");
	}

	public static void exists(String location) {
		exists(location, "[" + location + "] does not exist");
	}

	public static void exists(String location, String message) {
		isTrue(LocationUtils.exists(location), message);
	}

	public static void isExistingDir(File dir) {
		isExistingDir(dir, "[" + dir + "] is not an existing directory");
	}

	public static void isExistingDir(File dir, String message) {
		exists(dir, message);
		isTrue(dir.isDirectory(), message);
	}

	public static void exists(File file) {
		exists(file, "[" + file + "] does not exist");
	}

	public static void exists(File file, String message) {
		isTrue(file.exists(), message);
	}

	public static void isOdd(int i) {
		isOdd(i, "[" + i + "] is not an odd number");
	}

	public static void isOdd(int i, String message) {
		isTrue(i % 2 != 0, message);
	}

	public static void isEven(int i) {
		isEven(i, "[" + i + "] is not an even number");
	}

	public static void isEven(int i, String message) {
		isTrue(i % 2 == 0, message);
	}

	public static void isFalse(boolean condition) {
		isTrue(!condition);
	}

	public static void isFalse(boolean condition, String message) {
		isTrue(!condition, message);
	}

	public static void notBlank(String string) {
		isFalse(StringUtils.isBlank(string));
	}

	public static void noBlanks(String... strings) {
		noBlanksWithMsg(NO_BLANKS, strings);
	}

	public static void present(Optional<?>... optionals) {
		for (Optional<?> optional : optionals) {
			Assert.isTrue(optional.isPresent(), "Optional is required");
		}
	}

	public static void noBlanks(Optional<String> string) {
		noBlankOptionals(ImmutableList.of(string));
	}

	public static void noBlanks(Optional<String> s1, Optional<String> s2) {
		noBlankOptionals(ImmutableList.of(s1, s2));
	}

	public static void noBlanks(Optional<String> s1, Optional<String> s2, Optional<String> s3) {
		noBlankOptionals(ImmutableList.of(s1, s2, s3));
	}

	public static void noBlanks(Optional<String> s1, Optional<String> s2, Optional<String> s3, Optional<String> s4) {
		noBlankOptionals(ImmutableList.of(s1, s2, s3, s4));
	}

	public static void noBlankOptionals(List<Optional<String>> optionals) {
		for (Optional<String> optional : optionals) {
			if (optional.isPresent()) {
				noBlanks(optional.get());
			}
		}
	}

	/**
	 * @deprecated Use noBlankOptionals instead
	 * @see noBlankOptionals
	 */
	@Deprecated
	public static void noBlanksIfPresent(Optional<String>... optionals) {
		for (Optional<String> optional : optionals) {
			if (optional.isPresent()) {
				noBlanks(optional.get());
			}
		}
	}

	/**
	 * Assert that <code>strings</code> is not null and that none of the elements are blank
	 */
	public static void noBlanks(List<String> strings) {
		Assert.noNulls(strings);
		for (String string : strings) {
			noBlanksWithMsg(NO_BLANKS, string);
		}
	}

	public static void noNullStrings(String... strings) {
		notNull((Object) strings);
		for (String string : strings) {
			notNull(string, NO_NULLS);
		}
	}

	public static void noNulls(Object... objects) {
		noNullsWithMsg(NO_NULLS, objects);
	}

	public static void noNullsWithMsg(String msg, Object... objects) {
		for (Object object : objects) {
			notNull(object, msg);
		}
	}

	public static void noBlanksWithMsg(String msg, String... strings) {
		for (String string : strings) {
			isFalse(StringUtils.isBlank(string), msg);
		}
	}

	@Deprecated
	public static void notNull(Object... objects) {
		for (Object object : objects) {
			notNull(object);
		}
	}

	@Deprecated
	public static void notBlank(String... strings) {
		noBlanksWithMsg(NO_BLANKS, strings);
	}

	@Deprecated
	public static void noNulls(String msg, Object... objects) {
		for (Object object : objects) {
			notNull(object, msg);
		}
	}

}
