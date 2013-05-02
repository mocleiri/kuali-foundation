package org.kuali.common.impex.spring;

import java.util.Map;

import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.kuali.common.impex.KualiDatabase;
import org.kuali.common.impex.MpxLocationSupplier;
import org.kuali.common.impex.service.ImpexUtils;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.util.spring.SpringUtils;
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

    private static final String DB_VENDOR_KEY = "db.vendor";

    private static final String IMPEX_SCHEMA_LOCATION_KEY = "impex.schema.location";

	@Bean
	public SqlProducer impexProducer() {
		String vendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);
		Platform platform = PlatformFactory.getPlatformFor(vendor);
		SqlProducer producer = platform.getSqlProducer();
		producer.setBatchDataSizeLimit(batchConfig.impexBatchSize());
		producer.setBatchRowCountLimit(batchConfig.impexBatchRows());
		return producer;
	}

	@Bean
	public KualiDatabase impexDatabase() {
		String vendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);
		String location = SpringUtils.getProperty(env, IMPEX_SCHEMA_LOCATION_KEY);
		return ImpexUtils.getDatabase(vendor, location);
	}

	@Bean
	public Map<String, LocationSupplierSourceBean> impexExtensionMappings() {
		// This gets cloned for each .mpx file
		MpxLocationSupplier mls = new MpxLocationSupplier();
		mls.setDatabase(impexDatabase());
		mls.setProducer(impexProducer());

		// This hands out clones of MpxLocationSupplier, one for every .mpx file being parsed
		LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
		lssb.setSupplierClass(MpxLocationSupplier.class);
		lssb.setSupplierInstance(mls);

		// Add mpx as an extension JDBC can handle
		Map<String, LocationSupplierSourceBean> mappings = jdbcCommonConfig.jdbcExtensionMappings();
		mappings.put("mpx", lssb);
		return mappings;
	}
}
