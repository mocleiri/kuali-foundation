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

import org.apache.commons.lang3.StringUtils;

public abstract class Assert extends org.springframework.util.Assert {

	private static final String NO_NULLS = "null not allowed";
	private static final String NO_BLANKS = "blank strings not allowed";

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
