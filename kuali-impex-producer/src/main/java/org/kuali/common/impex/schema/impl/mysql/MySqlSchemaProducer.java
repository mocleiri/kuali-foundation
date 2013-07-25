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

package org.kuali.common.impex.schema.impl.mysql;

import java.util.List;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.impl.AbstractSchemaSqlProducer;
import org.kuali.common.impex.schema.impl.NoOpProvider;

public class MySqlSchemaProducer extends AbstractSchemaSqlProducer {

    private MySqlTableSqlProducer tableSqlProducer = new MySqlTableSqlProducer();

    private MySqlViewSqlProducer viewSqlProducer = new MySqlViewSqlProducer();

    private MySqlSequenceSqlProducer sequenceSqlProducer = new MySqlSequenceSqlProducer();

    private MySqlForeignKeySqlProducer foreignKeySqlProducer = new MySqlForeignKeySqlProducer();

    public MySqlSchemaProducer() {
        tableSqlProducer.setMappingProvider(new NoOpProvider());
    }

    public MySqlTableSqlProducer getTableSqlProducer() {
        return tableSqlProducer;
    }

    @Override
    public List<String> getTablesSql(List<Table> tables) {
        return tableSqlProducer.getTablesSql(tables);
    }

    @Override
    public List<String> getForeignKeySql(List<ForeignKey> foreignKeys) {
        return foreignKeySqlProducer.getForeignKeySql(foreignKeys);
    }

    @Override
    public List<String> getSequencesSql(List<Sequence> sequences) {
        return sequenceSqlProducer.getSequencesSql(sequences);
    }

    @Override
    public List<String> getViewsSql(List<View> views) {
        return viewSqlProducer.getViewsSql(views);
    }
}
