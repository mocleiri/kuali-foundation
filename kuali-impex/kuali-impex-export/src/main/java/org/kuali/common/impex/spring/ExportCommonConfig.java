package org.kuali.common.impex.spring;

import org.kuali.common.impex.data.service.DumpDataService;
import org.kuali.common.impex.data.service.impl.DefaultDumpDataService;
import org.kuali.common.impex.schema.service.ExtractSchemaService;
import org.kuali.common.impex.schema.service.impl.DefaultExtractSchemaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExportCommonConfig {

	@Bean
	public ExtractSchemaService exportExtractSchemaService() {
		return new DefaultExtractSchemaService();
	}

	@Bean
	public DumpDataService exportDumpDataService() {
		return new DefaultDumpDataService();
	}

}
