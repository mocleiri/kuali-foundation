package org.kuali.common.impex.spring;

import java.util.Map;

import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.impex.KualiDatabase;
import org.kuali.common.impex.MpxLocationSupplier;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, BatchConfig.class })
public class MpxSupplierConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig jdbcCommonConfig;

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

	@Bean
	public Map<String, LocationSupplierSourceBean> impexExtensionMappings() {
		MpxLocationSupplier mls = new MpxLocationSupplier();
		mls.setDatabase(impexDatabase());
		mls.setProducer(impexProducer());

		LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
		lssb.setSupplierClass(MpxLocationSupplier.class);
		lssb.setSupplierInstance(mls);

		// Add mpx as an extension JDBC can handle
		Map<String, LocationSupplierSourceBean> mappings = jdbcCommonConfig.jdbcExtensionMappings();
		mappings.put("mpx", lssb);
		return mappings;
	}
}
