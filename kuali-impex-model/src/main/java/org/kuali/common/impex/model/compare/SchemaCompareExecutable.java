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

package org.kuali.common.impex.model.compare;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.compare.service.SchemaCompareService;
import org.kuali.common.impex.model.util.CompareUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaCompareExecutable implements Executable {

    private static Logger log = LoggerFactory.getLogger(SchemaCompareExecutable.class.getSimpleName());

    Schema schema1;
    Schema schema2;
    SchemaCompareService compareService;
    Boolean skip = DEFAULT_EXECUTION_SKIP;

    public static final Boolean DEFAULT_EXECUTION_SKIP = false;

    public SchemaCompareExecutable(Boolean b) {
        this.skip = b;
    }

    @Override
    public void execute() {
        if(skip) {
            return;
        }

        SchemaCompareResult results = compareService.compare(schema1, schema2);

        for(TableDifference t : results.getTableDifferences()) {
            log.info(CompareUtils.tableDifferenceToString(t));
        }

        for (ForeignKeyDifference f : results.getForeignKeyDifferences()) {
            log.info(CompareUtils.foreignKeyDifferenceToString(f));
        }

        for (ViewDifference v : results.getViewDifferences()) {
            log.info(CompareUtils.viewDifferenceToString(v));
        }

        for (SequenceDifference s : results.getSequenceDifferences()) {
            log.info(CompareUtils.sequenceDifferenceToString(s));
        }
    }

    public SchemaCompareService getCompareService() {
        return compareService;
    }

    public void setCompareService(SchemaCompareService compareService) {
        this.compareService = compareService;
    }

    public Schema getSchema1() {
        return schema1;
    }

    public void setSchema1(Schema schema1) {
        this.schema1 = schema1;
    }

    public Schema getSchema2() {
        return schema2;
    }

    public void setSchema2(Schema schema2) {
        this.schema2 = schema2;
    }
}
