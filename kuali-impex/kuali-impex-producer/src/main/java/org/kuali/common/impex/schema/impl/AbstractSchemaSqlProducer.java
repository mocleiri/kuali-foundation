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

package org.kuali.common.impex.schema.impl;

import org.kuali.common.impex.schema.DataTypeMappingProvider;
import org.kuali.common.impex.schema.SchemaSqlProducer;

public abstract class AbstractSchemaSqlProducer implements SchemaSqlProducer {

    @Override
    public DataTypeMappingProvider getDataTypeMappingProvider() {
        return getTableSqlProducer().getMappingProvider();
    }

    @Override
    public void setDataTypeMappingProvider(DataTypeMappingProvider dataTypeMappingProvider) {
        getTableSqlProducer().setMappingProvider(dataTypeMappingProvider);
    }

    protected abstract AbstractTableSqlProducer getTableSqlProducer();

}
