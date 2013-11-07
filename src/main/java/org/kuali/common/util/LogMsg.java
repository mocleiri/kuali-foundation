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

/**
 * @deprecated
 */
@Deprecated
public class LogMsg {

	LoggerLevel level = LoggerLevel.INFO;
	String message;
	Object[] args;

	public LogMsg() {
		this(null, null, LoggerLevel.INFO);
	}

	public LogMsg(String message, Object[] args) {
		this(message, args, LoggerLevel.INFO);
	}

	public LogMsg(String message, Object[] args, LoggerLevel level) {
		this.message = message;
		this.args = args;
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
