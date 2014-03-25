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
package org.kuali.common.util.ignore;

import org.apache.commons.lang3.StringUtils;

public class PrefixSuffixIgnorer implements Ignore {

	String prefix;
	String suffix;

	public PrefixSuffixIgnorer() {
		this(null, null);
	}

	public PrefixSuffixIgnorer(String prefix, String suffix) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
	}

	@Override
	public boolean ignore(String line) {
		return StringUtils.startsWith(line, prefix) && StringUtils.endsWith(line, suffix);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
