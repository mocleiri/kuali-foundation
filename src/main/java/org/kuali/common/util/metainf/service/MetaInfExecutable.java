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
package org.kuali.common.util.metainf.service;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.ScanResult;

public final class MetaInfExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;

	private final boolean skip;
	private final List<MetaInfContext> contexts;
	private final MetaInfService service;

	public MetaInfExecutable(List<MetaInfContext> contexts, MetaInfService service) {
		this(contexts, service, DEFAULT_SKIP);
	}

	public MetaInfExecutable(List<MetaInfContext> contexts, MetaInfService service, boolean skip) {
		Assert.noNulls(contexts, service);
		this.skip = skip;
		this.contexts = contexts;
		this.service = service;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		List<ScanResult> results = service.scan(contexts);
		service.write(results);

	}

	public boolean isSkip() {
		return skip;
	}

	public List<MetaInfContext> getContexts() {
		return contexts;
	}

	public MetaInfService getService() {
		return service;
	}

}
