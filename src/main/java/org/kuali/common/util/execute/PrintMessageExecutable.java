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
package org.kuali.common.util.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintMessageExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(PrintMessageExecutable.class);

	public PrintMessageExecutable() {
		this(null);

	}

	public PrintMessageExecutable(String message) {
		this.message = message;
		this.skip = false;
	}

	String message;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution");
		} else {
			logger.info(message);
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
