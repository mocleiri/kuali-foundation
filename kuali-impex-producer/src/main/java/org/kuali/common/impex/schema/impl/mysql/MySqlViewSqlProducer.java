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
import org.kuali.common.impex.model.View;
import org.kuali.common.util.CollectionUtils;

public class MySqlViewSqlProducer {

    protected static final String DROP_PREFIX = "DROP VIEW IF EXISTS ";

    protected static final String CREATE_PREFIX = "CREATE VIEW ";
    protected static final String CREATE_AS_KEYWORD = " AS\n";


    public List<String> getViewsSql(List<View> views) {
        List<String> results = new ArrayList<String>();

        for (View view : CollectionUtils.toEmptyList(views)) {
            results.addAll(generateStatementsForView(view));
        }

        return results;
    }

    protected List<String> generateStatementsForView(View view) {
        List<String> results = new ArrayList<String>(2);

        StringBuilder sb = new StringBuilder();

        sb.append(DROP_PREFIX);
        sb.append(view.getName());
        sb.append(ProducerUtils.NEWLINE);

        results.add(sb.toString());
        sb = new StringBuilder();

        sb.append(CREATE_PREFIX);
        sb.append(view.getName());
        sb.append(CREATE_AS_KEYWORD);
        sb.append(view.getQueryString());
        sb.append(ProducerUtils.NEWLINE);
        results.add(sb.toString());

        return results;
    }
}
