/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultModelProvider implements ModelProvider {

	protected Schema schema;
	private Map<String, List<ForeignKey>> tableNamesToFks;

	public DefaultModelProvider(Schema s) {
		schema = s;

		buildFkMap();
	}

	protected void buildFkMap() {
		tableNamesToFks = new HashMap<String, List<ForeignKey>>();

		for (ForeignKey fk : getForeignKeys()) {
			if (!tableNamesToFks.containsKey(fk.getLocalTableName())) {
				tableNamesToFks.put(fk.getLocalTableName(), new ArrayList<ForeignKey>());
			}

			tableNamesToFks.get(fk.getLocalTableName()).add(fk);
		}
	}

	@Override
	public List<ForeignKey> getForeignKeys() {
		return schema.getForeignKeys();
	}

	@Override
	public List<Sequence> getSequences() {
		return schema.getSequences();
	}

	@Override
	public Map<String, List<ForeignKey>> getTableNameToForeignKeys() {
		return tableNamesToFks;
	}

	@Override
	public List<Table> getTables() {
		return schema.getTables();
	}

	@Override
	public List<View> getViews() {
		return schema.getViews();
	}
}
