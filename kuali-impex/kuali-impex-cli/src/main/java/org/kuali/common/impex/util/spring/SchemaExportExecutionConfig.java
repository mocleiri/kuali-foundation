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

package org.kuali.common.impex.util.spring;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.ExportSchemaExecutable;
import org.kuali.common.impex.schema.service.SchemaExtractionResult;
import org.kuali.common.impex.spring.SchemaExportConfig;
import org.kuali.common.impex.spring.SchemaExtractionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SchemaExportConfig.class, SchemaExtractionConfig.class })
public class SchemaExportExecutionConfig {

	@Autowired
	SchemaExtractionConfig extractionConfig;

	@Autowired
	SchemaExportConfig exportConfig;

	@Bean(initMethod = "execute")
	public ExportSchemaExecutable configuredExportExecutable() {

		Map<String, Class<? extends NamedElement>> locationMap = exportConfig.schemaLocations();

		ExportSchemaExecutable result = exportConfig.exportSchemaExecutable();

		SchemaExtractionResult extractionResult = executedResult();

		Map<String, Schema> schemaLocationMap = new HashMap<String, Schema>(locationMap.size());

		for (Map.Entry<String, Class<? extends NamedElement>> entry : locationMap.entrySet()) {
			String location = entry.getKey();
			Class<? extends NamedElement> elementClass = entry.getValue();

			// if a schema already exists for the location, append to it
			Schema schema = schemaLocationMap.get(location);
			if (schema == null) {
				// otherwise, make a new schema instance
				schema = new Schema();
				schemaLocationMap.put(location, schema);
			}

			if (elementClass.equals(Table.class)) {
				schema.getTables().addAll(extractionResult.getSchema().getTables());
			} else if (elementClass.equals(Sequence.class)) {
				schema.getSequences().addAll(extractionResult.getSchema().getSequences());
			} else if (elementClass.equals(View.class)) {
				schema.getViews().addAll(extractionResult.getSchema().getViews());
			} else if (elementClass.equals(ForeignKey.class)) {
				schema.getForeignKeys().addAll(extractionResult.getSchema().getForeignKeys());
			}
		}

		result.setSchemaLocations(schemaLocationMap);

		return result;
	}

	// TODO What is this all about?
	@Bean
	public SchemaExtractionResult executedResult() {
		return null;
		// extractionConfig.schemaExtractionExecutable().execute();
		// return extractionConfig.extractionResult();
	}

}
