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

package org.kuali.common.impex.spring;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.ExportSchemaException;
import org.kuali.common.impex.schema.ExportSchemaService;
import org.kuali.common.util.execute.Executable;
import org.springframework.beans.factory.annotation.Autowired;

public class ExportSchemaExecutable implements Executable {

    @Autowired
    ExportSchemaConfig exportSchemaConfig;

    @Autowired
    ExportSchemaService exportService;

    Boolean enabled;

    public static final Boolean DEFAULT_EXECUTE_ENABLED = true;

    public ExportSchemaExecutable() {
        this(DEFAULT_EXECUTE_ENABLED);
    }

    public ExportSchemaExecutable(Boolean b) {
        this.enabled = b;
    }

    @Override
    public void execute() {

        if(!enabled) {
            return;
        }

        Map<String, Schema> schemaLocations = exportSchemaConfig.schemaLocations();

        for (String location : schemaLocations.keySet()) {
            Writer writer;
            try {
                writer = new FileWriter(location);
            } catch (IOException e) {
                throw new RuntimeException("Could not open a file writer for location " + location, e);
            }
            try {
                exportService.exportSchema(schemaLocations.get(location), writer);
            } catch (ExportSchemaException e) {
                throw new RuntimeException("Unable to persist schema to location " + location, e);
            }
        }

    }

}
