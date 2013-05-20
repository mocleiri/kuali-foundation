package org.kuali.common.impex.spring;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import liquibase.exception.DatabaseException;
import liquibase.snapshot.InvalidExampleException;
import org.kuali.common.impex.MpxLocationSupplier;
import org.kuali.common.impex.service.MySqlProducer;
import org.kuali.common.impex.service.OracleProducer;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcCommonConfig.class, BatchConfig.class, JdbcDataSourceConfig.class, LiquibaseModelProviderConfig.class })
public class MpxSupplierConfig {

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig jdbcCommonConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	BatchConfig batchConfig;

	@Autowired
	LiquibaseModelProviderConfig liquibaseModelConfig;

	private static final String DB_VENDOR_KEY = "db.vendor";

    @Bean Map<String, SqlProducer> vendorProducerMap() {
        Map<String, SqlProducer> results = new HashMap<String, SqlProducer>();

        results.put(OracleProducer.SUPPORTED_VENDOR, configureProducer(new OracleProducer()));
        results.put(MySqlProducer.SUPPORTED_VENDOR, configureProducer(new MySqlProducer()));

        return results;
    }

    private SqlProducer configureProducer(SqlProducer sqlProducer) {

        sqlProducer.setBatchDataSizeLimit(batchConfig.impexBatchSize());
        sqlProducer.setBatchRowCountLimit(batchConfig.impexBatchRows());

        return sqlProducer;
    }

    @Bean
	public SqlProducer impexProducer() {
		String vendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);

		return vendorProducerMap().get(vendor);
	}

	@Bean
	public Map<String, LocationSupplierSourceBean> impexExtensionMappings() throws DatabaseException, InvalidExampleException, SQLException {
		// This gets cloned for each .mpx file
		MpxLocationSupplier mls = new MpxLocationSupplier();
		mls.setModelProvider(liquibaseModelConfig.liquibaseModelProvider());
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
