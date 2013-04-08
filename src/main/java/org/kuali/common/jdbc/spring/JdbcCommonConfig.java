package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.jdbc.DefaultJdbcService;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.jdbc.supplier.LocationSupplier;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.jdbc.supplier.LocationSuppliersFactoryBean;
import org.kuali.common.jdbc.supplier.SqlLocationSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import(JdbcProjectConfig.class)
public class JdbcCommonConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	JdbcProjectConfig projectConfig;

	@Bean
	public SqlReader jdbcSqlReader() {
		return new DefaultSqlReader();
	}

	@Bean
	public JdbcService jdbcService() {
		return new DefaultJdbcService();
	}

	@Bean
	public Map<String, LocationSupplierSourceBean> jdbcExtensionMappings() {
		Project project = projectConfig.jdbcProject();

		SqlLocationSupplier sls = new SqlLocationSupplier();
		sls.setReader(jdbcSqlReader());
		sls.setEncoding(project.getEncoding());

		LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
		lssb.setSupplierClass(SqlLocationSupplier.class);
		lssb.setSupplierInstance(sls);

		Map<String, LocationSupplierSourceBean> map = new HashMap<String, LocationSupplierSourceBean>();
		map.put("sql", lssb);
		return map;
	}

	public List<SqlSupplier> getSqlSuppliers(String property) {
		LocationSuppliersFactoryBean factory = new LocationSuppliersFactoryBean();
		factory.setProperty(property);
		factory.setEnv(env);
		factory.setExtensionMappings(jdbcExtensionMappings());
		List<LocationSupplier> list = factory.getObject();
		return new ArrayList<SqlSupplier>(list);
	}
}
