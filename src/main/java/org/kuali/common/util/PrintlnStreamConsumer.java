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

import java.io.PrintStream;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.ignore.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintlnStreamConsumer implements StreamConsumer {

	private static final Logger logger = LoggerFactory.getLogger(PrintlnStreamConsumer.class);

	PrintStream printStream;
	long lineCount = 0;
	long skipCount = 0;
	List<Ignore> ignorers;
	boolean enableIgnorers = true;

	public PrintlnStreamConsumer() {
		this(null);
	}

	public PrintlnStreamConsumer(PrintStream printStream) {
		this(printStream, null);
	}

	public PrintlnStreamConsumer(PrintStream printStream, List<Ignore> ignorers) {
		this.printStream = printStream;
		this.ignorers = ignorers;
	}

	@Override
	public void consumeLine(String line) {
		lineCount++;
		if (enableIgnorers && ignore(line, ignorers)) {
			skipCount++;
			Object[] args = { skipCount, lineCount, line };
			logger.debug("{} Skipping line {} [{}]", args);
		} else {
			printStream.println(line);
		}
	}

	protected boolean ignore(String line, List<Ignore> ignorers) {
		if (ignorers == null) {
			return false;
		}
		for (Ignore ignorer : ignorers) {
			if (ignorer.ignore(line)) {
				return true;
			}
		}
		return false;
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public long getLineCount() {
		return lineCount;
	}

	public void setLineCount(long lineCount) {
		this.lineCount = lineCount;
	}

	public long getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(long skipCount) {
		this.skipCount = skipCount;
	}

	public List<Ignore> getIgnorers() {
		return ignorers;
	}

	public void setIgnorers(List<Ignore> ignorers) {
		this.ignorers = ignorers;
	}

}
