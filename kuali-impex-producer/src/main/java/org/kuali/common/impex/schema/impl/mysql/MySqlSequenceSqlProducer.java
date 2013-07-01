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
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.util.CollectionUtils;

public class MySqlSequenceSqlProducer {

    protected static final String DROP_PREFIX = "DROP TABLE IF EXISTS ";

    protected static final String CREATE_PREFIX = "CREATE TABLE ";
    protected static final String CREATE_SUFFIX = "\n" +
            "(\n" +
            "\tID BIGINT(19) NOT NULL AUTO_INCREMENT, PRIMARY KEY (ID)\n" +
            ") ENGINE MyISAM\n";

    protected static final String ALTER_PREFIX = "ALTER TABLE ";

    protected static final String ALTER_INCREMENT_START = " AUTO_INCREMENT = ";

    public List<String> getSequencesSql(List<Sequence> sequences) {
        List<String> results = new ArrayList<String>();

        for (Sequence seq : CollectionUtils.toEmptyList(sequences)) {
            results.add(generateDropSequenceStatment(seq));

            results.add(generateCreateSequenceStatement(seq));

            results.add(generateAlterStatement(seq));
        }

        return results;
    }

    protected String generateDropSequenceStatment(Sequence sequence) {
        StringBuilder sb = new StringBuilder();

        sb.append(DROP_PREFIX);
        sb.append(sequence.getName());
        sb.append(ProducerUtils.NEWLINE);

        return sb.toString();
    }

    protected String generateCreateSequenceStatement(Sequence sequence) {
        StringBuilder sb = new StringBuilder();

        sb.append(CREATE_PREFIX);
        sb.append(sequence.getName());
        sb.append(CREATE_SUFFIX);

        return sb.toString();
    }

    protected String generateAlterStatement(Sequence sequence) {
        StringBuilder sb = new StringBuilder();

        sb.append(ALTER_PREFIX);
        sb.append(sequence.getName());
        sb.append(ALTER_INCREMENT_START);
        sb.append(sequence.getStartValue());
        sb.append(ProducerUtils.NEWLINE);

        return sb.toString();
    }

}
