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

package org.kuali.common.jalc.schema.impl.oracle;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jalc.model.View;
import org.kuali.common.util.CollectionUtils;

public class OracleViewSqlProducer {

    protected static final String CREATE_VIEW_HEADER = "CREATE OR REPLACE FORCE VIEW ";
    protected static final String CREATE_VIEW_AS_KEYWORD = " AS \n";
    protected static final String CREATE_VIEW_FOOTER = "\n";

    public List<String> getViewsSql(List<View> views) {
        List<String> results = new ArrayList<String>();

        for (View view : CollectionUtils.toEmptyList(views)) {
            results.add(generateSqlForView(view));
        }

        return results;
    }

    protected String generateSqlForView(View view) {
        StringBuilder sb = new StringBuilder();

        sb.append(CREATE_VIEW_HEADER);
        sb.append(view.getName());
        sb.append(CREATE_VIEW_AS_KEYWORD);
        sb.append(view.getQueryString());
        sb.append(CREATE_VIEW_FOOTER);

        return sb.toString();
    }
}
