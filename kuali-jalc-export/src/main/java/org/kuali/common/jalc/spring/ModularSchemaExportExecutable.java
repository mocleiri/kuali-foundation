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

package org.kuali.common.jalc.spring;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.kuali.common.jalc.model.Schema;
import org.kuali.common.jalc.schema.ExportSchemaException;
import org.kuali.common.jalc.schema.ExportSchemaService;
import org.kuali.common.util.execute.Executable;

public class ModularSchemaExportExecutable implements Executable {

    public static final Boolean DEFAULT_EXECUTE_ENABLED = true;

    protected String outputLocation;

    protected Schema schema;

    protected ExportSchemaService exportService;

    private boolean execute;

    public ModularSchemaExportExecutable(String location, Schema schema, ExportSchemaService service, boolean execute) {
        this.outputLocation = location;
        this.schema = schema;
        this.exportService = service;
        this.execute = execute;
    }

    @Override
    public void execute() {

        if(!execute) {
            return;
        }

        Writer writer;
        try {
            writer = new FileWriter(outputLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not open a file writer for location " + outputLocation, e);
        }
        try {
            exportService.exportSchema(schema, writer);
        } catch (ExportSchemaException e) {
            throw new RuntimeException("Unable to persist schema to location " + outputLocation, e);
        }
    }
}
