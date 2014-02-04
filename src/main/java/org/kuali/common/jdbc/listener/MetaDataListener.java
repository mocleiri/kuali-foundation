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
package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.FormatUtils;

/**
 * @deprecated
 */
@Deprecated
public class MetaDataListener extends NoOpSqlListener {

	@Override
	public void beforeMetaData(SqlMetaDataEvent event) {
		JdbcContext context = event.getContext();
		List<SqlSupplier> suppliers = context.getSuppliers();
		System.out.print("[INFO] Accumulating SQL metadata from " + suppliers.size() + " suppliers - ");
	}

	@Override
	public void afterMetaData(SqlMetaDataEvent event) {
		String elapsed = FormatUtils.getTime(event.getStopTimeMillis() - event.getStartTimeMillis());
		System.out.println("[" + elapsed + "]");
	}

}
