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

package org.kuali.common.impex;

import java.util.List;

import org.kuali.common.impex.data.DataExportExecutable;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.ModularSchemaExportExecutable;
import org.kuali.common.impex.schema.service.SchemaExtractionExecutable;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class DatabaseExportExecutable implements Executable {

    SchemaExtractionExecutable schemaExtractionExecutable;

    DataExportExecutable dataExportExecutable;

    List<ModularSchemaExportExecutable> schemaExecutables;

    @Override
    public void execute() {
        schemaExtractionExecutable.execute();

        Schema schema = schemaExtractionExecutable.getResult().getSchema();

        Assert.notNull(schema, "Schema from extraction results is null");

        for(ModularSchemaExportExecutable schemaExportExecutable : schemaExecutables) {
            schemaExportExecutable.setSchema(schema);

            schemaExportExecutable.execute();
        }

        dataExportExecutable.setSchema(schema);

        dataExportExecutable.execute();
    }

    public DataExportExecutable getDataExportExecutable() {
        return dataExportExecutable;
    }

    public void setDataExportExecutable(DataExportExecutable dataExportExecutable) {
        this.dataExportExecutable = dataExportExecutable;
    }

    public List<ModularSchemaExportExecutable> getSchemaExecutables() {
        return schemaExecutables;
    }

    public void setSchemaExecutables(List<ModularSchemaExportExecutable> schemaExecutables) {
        this.schemaExecutables = schemaExecutables;
    }

    public SchemaExtractionExecutable getSchemaExtractionExecutable() {
        return schemaExtractionExecutable;
    }

    public void setSchemaExtractionExecutable(SchemaExtractionExecutable schemaExtractionExecutable) {
        this.schemaExtractionExecutable = schemaExtractionExecutable;
    }
}
