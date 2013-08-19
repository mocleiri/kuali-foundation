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

	public static void exists(String location) {
		exists(location, "[Assertion failed] - [" + location + "] does not exist");
	}

	public static void exists(String location, String message) {
		isTrue(LocationUtils.exists(location), message);
	}

	public static void isExistingDir(File dir) {
		isExistingDir(dir, "[Assertion failed] - [" + dir + "] is not an existing directory");
	}

	public static void isExistingDir(File dir, String message) {
		exists(dir, message);
		isTrue(dir.isDirectory(), message);
	}

	public static void exists(File file) {
		exists(file, "[Assertion failed] - [" + file + "] does not exist");
	}

	public static void exists(File file, String message) {
		isTrue(file.exists(), message);
	}

	public static void isOdd(int i) {
		isOdd(i, "[Assertion failed] - [" + i + "] is not an odd number");
	}

	public static void isOdd(int i, String message) {
		isTrue(i % 2 != 0, message);
	}

	public static void isEven(int i) {
		isEven(i, "[Assertion failed] - [" + i + "] is not an even number");
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

	@Deprecated
	public static void notNull(Object... objects) {
		for (Object object : objects) {
			notNull(object);
		}
	}

	public static void notBlank(String string) {
		isFalse(StringUtils.isBlank(string));
	}

	@Deprecated
	public static void notBlank(String... strings) {
		noBlanksWithMsg("blank strings not allowed", strings);
	}

	public static void noBlanks(String... strings) {
		for (String string : strings) {
			isFalse(StringUtils.isBlank(string));
		}
	}

	public static void noBlanksWithMsg(String msg, String... strings) {
		for (String string : strings) {
			isFalse(StringUtils.isBlank(string));
		}
	}

	public static void noNulls(Object... objects) {
		noNulls("null is not allowed", objects);
	}

	public static void noNulls(String msg, Object... objects) {
		for (Object object : objects) {
			notNull(object, msg);
		}
	}

}
