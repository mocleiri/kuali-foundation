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
package org.kuali.common.util.property.processor;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PropertyUtils;

public final class TrimmingProcessor implements PropertyProcessor {

	private final List<String> includes;
	private final List<String> excludes;

	public TrimmingProcessor(String exclude) {
		this(CollectionUtils.noNullsSingletonList(exclude));
	}

	public TrimmingProcessor(List<String> excludes) {
		this(Collections.<String> emptyList(), excludes);
	}

	public TrimmingProcessor(List<String> includes, List<String> excludes) {
		Assert.noNulls(includes, excludes);
		this.includes = includes;
		this.excludes = excludes;
	}

	@Override
	public void process(Properties properties) {
		PropertyUtils.trim(properties, includes, excludes);
	}

	public List<String> getIncludes() {
		return includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}
}
