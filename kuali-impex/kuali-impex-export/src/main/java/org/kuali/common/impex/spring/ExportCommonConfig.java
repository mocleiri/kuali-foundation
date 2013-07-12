package org.kuali.common.impex.spring;

import org.kuali.common.impex.data.DumpDataExecutable;
import org.kuali.common.impex.data.service.DumpDataService;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.impex.schema.execute.DumpSchemasExecutable;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
import org.kuali.common.impex.schema.service.ExtractSchemaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportCommonConfig {

	@Bean
	public DumpSchemaService exportDumpSchemaService() {
		return DumpSchemasExecutable.DEFAULT_SERVICE;
	}

	@Bean
	public ExtractSchemaService exportExtractSchemaService() {
		return ExtractSchemaExecutable.DEFAULT_SERVICE;
	}

	@Bean
	public DumpDataService exportDumpDataService() {
		return DumpDataExecutable.DEFAULT_SERVICE;
	}
}
