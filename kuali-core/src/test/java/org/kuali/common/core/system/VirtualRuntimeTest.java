/**
 * Copyright 2014-2014 The Kuali Foundation
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
package org.kuali.common.core.system;

import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.text.NumberFormat;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;

public class VirtualRuntimeTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		NumberFormat nf = NumberFormat.getPercentInstance();
		VirtualRuntime runtime = VirtualRuntime.create();
		JsonService service = new JacksonJsonService();
		logger.info(format("\n%s", service.writeString(runtime)));
		Memory mem = runtime.getMemory();
		double percent = (mem.getUsed() * 1d) / mem.getFree();
		Object[] args = { runtime.getProcessors(), nf.format(percent), FormatUtils.getSize(runtime.getMemory().getFree()) };
		logger.info(format("processors: %s  memory: %s of %s", args));
	}

}
