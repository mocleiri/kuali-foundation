package org.kuali.common.impex.spring;

import org.kuali.common.impex.data.DumpDataExecutable;
import org.kuali.common.impex.data.service.DumpDataService;
import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.impex.schema.execute.DumpSchemasExecutable;
import org.kuali.common.impex.schema.service.ExtractSchemaExecutable;
import org.kuali.common.impex.schema.service.ExtractSchemaService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ExportCommonConfig {

	private static final String DUMP_SCHEMA_SERVICE_KEY = "impex.dump.schema.service";
	private static final String DUMP_DATA_SERVICE_KEY = "impex.dump.data.service";
	private static final String EXTRACT_SCHEMA_SERVICE_KEY = "impex.extract.schema.service";

	@Autowired
	Environment env;

	@Bean
	public DumpSchemaService exportDumpSchemaService() {
		return SpringUtils.getInstance(env, DUMP_SCHEMA_SERVICE_KEY, DumpSchemasExecutable.DEFAULT_SERVICE.getClass());
	}

	@Bean
	public ExtractSchemaService exportExtractSchemaService() {
		return SpringUtils.getInstance(env, EXTRACT_SCHEMA_SERVICE_KEY, ExtractSchemaExecutable.DEFAULT_SERVICE.getClass());
	}

	@Bean
	public DumpDataService exportDumpDataService() {
		return SpringUtils.getInstance(env, DUMP_DATA_SERVICE_KEY, DumpDataExecutable.DEFAULT_SERVICE.getClass());
	}
}
