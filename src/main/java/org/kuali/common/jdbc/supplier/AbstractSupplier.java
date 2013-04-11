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

import org.kuali.common.jdbc.SqlMetaData;

public abstract class AbstractSupplier implements SqlSupplier {

	protected SqlMetaData metaData;

	@Override
	public SqlMetaData getMetaData() {
		return metaData;
	}

	@Override
	public int compareTo(SqlSupplier other) {
		return Double.compare(metaData.getSize(), other.getMetaData().getSize());
	}

}
