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
package org.kuali.common.jdbc.supplier;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.jdbc.SqlMetaData;
import org.springframework.util.Assert;

/**
 * Supply SQL from strings that have one SQL statement each
 * 
 * @deprecated
 */
@Deprecated
public class SimpleStringSupplier extends AbstractSupplier {

	List<String> strings;
	boolean closed = true;

	public SimpleStringSupplier() {
		this((String) null);
	}

	public SimpleStringSupplier(String sql) {
		this(Arrays.asList(sql));
	}

	public SimpleStringSupplier(List<String> strings) {
		super();
		this.strings = strings;
	}

	@Override
	public void open() {
		// Make sure we've got something to work with
		Assert.notNull(strings, "strings is null");
		Assert.isTrue(closed, "closed is false");
		this.closed = false;
	}

	@Override
	public List<String> getSql() {
		if (closed) {
			return null;
		} else {
			this.closed = true;
			return strings;
		}
	}

	@Override
	public void close() {
		this.closed = true;
	}

	@Override
	public void fillInMetaData() {
		int count = strings.size();
		long size = 0;
		for (String string : strings) {
			size += string.length();
		}
		this.metaData = new SqlMetaData(count, size);
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
