/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc.supplier;

import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;
import org.springframework.util.Assert;

public class StringSupplier implements SqlSupplier {

	protected int index = 0;

	List<String> strings;

	@Override
	public void open() {

		// Make sure we've got something to work with
		Assert.notNull(strings, "strings is null");

		// Reset index to zero
		index = 0;
	}

	@Override
	public String getSql() {
		if (index < strings.size()) {
			return strings.get(index++);
		} else {
			return null;
		}
	}

	@Override
	public void close() {
		// nothing to do
	}

	@Override
	public SqlMetaData getSqlMetaData() {
		int count = strings.size();
		long size = 0;
		for (String string : strings) {
			size += string.length();
		}
		return new SqlMetaData(count, size);
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
