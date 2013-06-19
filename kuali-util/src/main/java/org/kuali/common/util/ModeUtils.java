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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeUtils {

	private static final Logger logger = LoggerFactory.getLogger(ModeUtils.class);

	public static final void validate(Mode mode, String msg) {
		validate(mode, msg, msg);
	}

	public static final void validate(Mode mode, String msg, String errMsg) {
		validate(mode, msg, null, errMsg);
	}

	public static final void validate(Mode mode, String msg, Object arg, String errMsg) {
		validate(mode, msg, arg, null, errMsg);
	}

	public static final void validate(Mode mode, String msg, Object arg1, Object arg2, String errMsg) {
		validate(mode, msg, getArgs(arg1, arg2), errMsg);
	}

	public static final void validate(Mode mode, String msg, Object[] args, String errMsg) {
		switch (mode) {
		case IGNORE:
			return;
		case DEBUG:
			logger.debug(msg, args);
			return;
		case INFORM:
			logger.info(msg, args);
			return;
		case WARN:
			logger.warn(msg, args);
			return;
		case ERROR:
			logger.error(msg, args);
			throw new IllegalStateException(errMsg);
		default:
			throw new IllegalArgumentException("Mode '" + mode + "' is unknown");
		}
	}

	protected static final Object[] getArgs(Object arg1, Object arg2) {
		if (arg1 == null && arg2 == null) {
			return null;
		}
		List<Object> args = new ArrayList<Object>();
		if (arg1 != null) {
			args.add(arg1);
		}
		if (arg2 != null) {
			args.add(arg2);
		}
		return CollectionUtils.toObjectArray(args);
	}

}
