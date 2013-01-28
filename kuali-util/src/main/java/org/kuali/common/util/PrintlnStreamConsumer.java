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

import java.io.PrintStream;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintlnStreamConsumer implements StreamConsumer {

	private static final Logger logger = LoggerFactory.getLogger(PrintlnStreamConsumer.class);

	public PrintlnStreamConsumer() {
		this(null);
	}

	public PrintlnStreamConsumer(PrintStream printStream) {
		this(printStream, null, null);
	}

	public PrintlnStreamConsumer(PrintStream printStream, String skipPrefix, String skipSuffix) {
		super();
		this.printStream = printStream;
		this.skipPrefix = skipPrefix;
		this.skipSuffix = skipSuffix;
	}

	PrintStream printStream;
	long lineCount = 0;
	String skipPrefix;
	String skipSuffix;

	@Override
	public void consumeLine(String line) {
		lineCount++;
		if (isMatch(line, skipPrefix, skipSuffix)) {
			logger.info("Skipping line {} [{}]", lineCount, line);
		} else {
			printStream.println(line);
		}
	}

	protected boolean isMatch(String line, String prefix, String suffix) {
		boolean startsWith = StringUtils.startsWith(line, prefix);
		boolean endsWith = StringUtils.endsWith(line, suffix);
		return (startsWith && endsWith);
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public String getSkipPrefix() {
		return skipPrefix;
	}

	public void setSkipPrefix(String skipPrefix) {
		this.skipPrefix = skipPrefix;
	}

	public String getSkipSuffix() {
		return skipSuffix;
	}

	public void setSkipSuffix(String skipSuffix) {
		this.skipSuffix = skipSuffix;
	}

}
