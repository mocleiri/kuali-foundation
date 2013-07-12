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

import java.io.IOException;
import java.util.List;

import org.kuali.common.util.spring.metainf.MetaInfContext;
import org.kuali.common.util.MetaInfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class MetaInfExecutable implements Executable {

    private static final Logger logger = LoggerFactory.getLogger(MetaInfExecutable.class);

	List<MetaInfContext> contexts;
	boolean skip;

    public final static boolean DEFAULT_EXECUTION_SKIP = false;

	@Override
	public void execute() {

        logger.info("STARTING EXECUTE");

		if (skip) {
			return;
		}

		Assert.notNull(contexts, "contexts are null");

		try {
			MetaInfUtils.scanAndCreateFiles(contexts);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

        logger.info("ENDING EXECUTE");

	}

	public List<MetaInfContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<MetaInfContext> contexts) {
		this.contexts = contexts;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
