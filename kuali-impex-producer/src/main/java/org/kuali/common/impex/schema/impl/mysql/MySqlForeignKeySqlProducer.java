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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.ProducerUtils;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.ForeignKeyConstraintType;
import org.kuali.common.impex.schema.impl.AbstractForeignKeySqlProducer;
import org.kuali.common.util.CollectionUtils;

public class MySqlForeignKeySqlProducer extends AbstractForeignKeySqlProducer {

    protected static final String PREFIX = "ALTER TABLE ";
    protected static final String NAME_PREFIX = "\n" +
            "\tADD CONSTRAINT ";

    protected static final String LOCAL_COLUMNS_PREFIX = " FOREIGN KEY (";

    protected static final String FOREIGN_TABLE_PREFIX = ")\n" +
            "\tREFERENCES ";
    protected static final String FOREIGN_COLUMNS_PREFIX = " (";
    protected static final String FOREIGN_COLUMNS_SUFFIX = ")";

    protected static final String ON_UPDATE_PREFIX = "\tON UPDATE ";
    protected static final String ON_DELETE_PREFIX = "\tON DELETE ";

    public List<String> getForeignKeySql(List<ForeignKey> foreignKeys) {
        List<String> results = new ArrayList<String>();

        for (ForeignKey fk : CollectionUtils.toEmptyList(foreignKeys)) {

            results.add(generateForeignKeyStatement(fk));
        }

        return results;
    }

    private String generateForeignKeyStatement(ForeignKey fk) {
        StringBuilder sb = new StringBuilder();

        sb.append(PREFIX);
        sb.append(fk.getLocalTableName());
        sb.append(NAME_PREFIX);
        sb.append(fk.getName());
        sb.append(LOCAL_COLUMNS_PREFIX);
        sb.append(CollectionUtils.getCSV(fk.getLocalColumnNames()));
        sb.append(FOREIGN_TABLE_PREFIX);
        sb.append(fk.getForeignTableName());
        sb.append(FOREIGN_COLUMNS_PREFIX);
        sb.append(CollectionUtils.getCSV(fk.getForeignColumnNames()));
        sb.append(FOREIGN_COLUMNS_SUFFIX);
        sb.append(ProducerUtils.NEWLINE);

        // restrict is the default behavior for MySQL foreign keys,
        // so no keywords are needed for that constraint type
        if(fk.getOnUpdate() != null && fk.getOnUpdate() != ForeignKeyConstraintType.RESTRICT) {
            sb.append(ON_UPDATE_PREFIX);
            sb.append(translateForeignKeyConstraint(fk.getOnUpdate()));
            sb.append(ProducerUtils.NEWLINE);
        }

        if(fk.getOnDelete() != null && fk.getOnDelete() != ForeignKeyConstraintType.RESTRICT) {
            sb.append(ON_DELETE_PREFIX);
            sb.append(translateForeignKeyConstraint(fk.getOnDelete()));
            sb.append(ProducerUtils.NEWLINE);
        }

        return sb.toString();
    }
}
