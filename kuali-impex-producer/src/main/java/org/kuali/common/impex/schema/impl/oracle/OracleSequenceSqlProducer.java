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

package org.kuali.common.impex.schema.impl.oracle;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.model.Sequence;
import org.kuali.common.util.CollectionUtils;

public class OracleSequenceSqlProducer {

    protected static final String DROP_PREFIX =
            "DECLARE temp NUMBER;\n" +
            "BEGIN\n" +
            "\tSELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = '";
    protected static final String DROP_MIDDLE = "';\n" +
            "\tIF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE ";
    protected static final String DROP_SUFFIX =
            "'; END IF;\n" +
            "END;\n";

    protected static final String CREATE_PREFIX = "CREATE SEQUENCE ";
    protected static final String CREATE_INCREMENT_START = " INCREMENT BY 1 START WITH ";
    protected static final String CREATE_SUFFIX =
            " NOMAXVALUE NOCYCLE NOCACHE ORDER\n";

    public List<String> getSequencesSql(List<Sequence> sequences) {
        List<String> results = new ArrayList<String>();

        for (Sequence seq : CollectionUtils.toEmptyList(sequences)) {
            results.add(generateDropSequenceStatment(seq));

            results.add(generateCreateSequenceStatement(seq));
        }

        return results;
    }

    protected String generateCreateSequenceStatement(Sequence sequence) {
        StringBuilder sb = new StringBuilder();

        sb.append(CREATE_PREFIX);
        sb.append(sequence.getName());
        sb.append(CREATE_INCREMENT_START);
        sb.append(sequence.getStartValue());
        sb.append(CREATE_SUFFIX);

        return sb.toString();
    }

    protected String generateDropSequenceStatment(Sequence sequence) {
        StringBuilder sb = new StringBuilder();

        sb.append(DROP_PREFIX);
        sb.append(sequence.getName());
        sb.append(DROP_MIDDLE);
        sb.append(sequence.getName());
        sb.append(DROP_SUFFIX);

        return sb.toString();
    }
}
