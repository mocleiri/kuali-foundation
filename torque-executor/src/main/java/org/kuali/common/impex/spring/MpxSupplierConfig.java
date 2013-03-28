package org.kuali.common.impex.spring;

import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.impex.KualiDatabase;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(BatchConfig.class)
public class MpxSupplierConfig {

	@Autowired
	Environment env;

	@Autowired
	BatchConfig batchConfig;

	@Bean
	public SqlProducer impexProducer() {
		String vendor = SpringUtils.getProperty(env, "db.vendor");
		Platform platform = PlatformFactory.getPlatformFor(vendor);
		SqlProducer producer = platform.getSqlProducer();
		producer.setBatchDataSizeLimit(batchConfig.impexBatchSize());
		producer.setBatchRowCountLimit(batchConfig.impexBatchRows());
		return producer;
	}

	@Bean
	public KualiDatabase impexDatabase() {
		String vendor = SpringUtils.getProperty(env, "db.vendor");
		String location = SpringUtils.getProperty(env, "impex.schema.location");
		KualiXmlToAppData parser = new KualiXmlToAppData(vendor);
		try {
			return parser.parseResource(location);
		} catch (EngineException e) {
			throw new IllegalStateException(e);
		}
	}
}
