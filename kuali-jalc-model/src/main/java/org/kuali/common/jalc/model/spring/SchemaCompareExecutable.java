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

package org.kuali.common.jalc.model.spring;

import java.util.logging.Logger;

import org.kuali.common.jalc.model.compare.ForeignKeyDifference;
import org.kuali.common.jalc.model.compare.SchemaCompare;
import org.kuali.common.jalc.model.compare.SchemaCompareResult;
import org.kuali.common.jalc.model.compare.SequenceDifference;
import org.kuali.common.jalc.model.compare.TableDifference;
import org.kuali.common.jalc.model.compare.ViewDifference;
import org.kuali.common.jalc.model.util.CompareUtils;
import org.kuali.common.util.execute.Executable;

public class SchemaCompareExecutable implements Executable {

    private static Logger log = Logger.getLogger(SchemaCompareExecutable.class.getSimpleName());

    SchemaCompareConfig config;
    Boolean enabled;

    public static final Boolean DEFAULT_EXECUTE_ENABLED = true;

    public SchemaCompareExecutable(Boolean b) {
        this.enabled = b;
    }

    @Override
    public void execute() {
        if(!enabled) {
            return;
        }

        SchemaCompareResult results = config.schemaCompare().compare();

        for(TableDifference t : results.getTableDifferences()) {
            log.info(CompareUtils.tableDifferenceToString(t));
        }

        for (ForeignKeyDifference f : results.getForeignKeyDifferences()) {

        }

        for (ViewDifference v : results.getViewDifferences()) {

        }

        for (SequenceDifference s : results.getSequenceDifferences()) {

        }
    }

    public SchemaCompareConfig getConfig() {
        return config;
    }

    public void setConfig(SchemaCompareConfig config) {
        this.config = config;
    }
}
