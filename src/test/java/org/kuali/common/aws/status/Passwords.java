/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.status;

import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public final class Passwords {

	private static final String KEY = "enc.password";
	private static final File SETTINGS = getSettingsXmlFile();
	private static final Logger logger = newLogger();
	private static String password;

	public static String getEncPassword() {
		if (password == null) {
			password = getPassword();
		}
		return password;
	}

	private static String getPassword() {
		Optional<String> sys = getSystemPassword();
		if (sys.isPresent()) {
			logger.info(String.format("Located [%s] in system properties", KEY));
			return Str.reveal(sys.get());
		}
		Optional<String> settings = getSettingsXmlPassword();
		if (settings.isPresent()) {
			logger.info(String.format("Located [%s] in [%s]", KEY, SETTINGS));
			return Str.reveal(settings.get());
		} else {
			throw Exceptions.illegalState(String.format("[%s] was not found in system properties or [%s]", KEY, SETTINGS));
		}
	}

	protected static Optional<String> getSystemPassword() {
		if (System.getProperty(KEY) != null) {
			return Optional.of(System.getProperty(KEY).trim());
		} else {
			return Optional.absent();
		}
	}

	protected static File getSettingsXmlFile() {
		String path = System.getProperty("user.home") + "/.m2/settings.xml";
		return new CanonicalFile(path);
	}

	protected static Optional<String> getSettingsXmlPassword() {
		File file = getSettingsXmlFile();
		if (!file.exists()) {
			return Optional.absent();
		}
		try {
			String contents = FileUtils.readFileToString(file);
			String password = StringUtils.substringBetween(contents, "<" + KEY + ">", "</" + KEY + ">");
			if (!StringUtils.isBlank(password)) {
				return Optional.of(StringUtils.trim(password));
			} else {
				return Optional.absent();
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

}
