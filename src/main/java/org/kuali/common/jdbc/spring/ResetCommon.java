package org.kuali.common.jdbc.spring;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.jdbc.DefaultJdbcService;
import org.kuali.common.jdbc.DefaultSqlReader;
import org.kuali.common.jdbc.JdbcService;
import org.kuali.common.jdbc.SqlReader;
import org.kuali.common.jdbc.supplier.LocationSupplierSourceBean;
import org.kuali.common.jdbc.supplier.SqlLocationSupplier;
import org.kuali.common.util.Project;
import org.kuali.common.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ResetCommon {

	@Autowired
	Environment env;

	@Bean
	public Project project() {
		return ProjectUtils.getProject("org.kuali.common:kuali-jdbc");
	}

	@Bean
	public SqlReader sqlReader() {
		return new DefaultSqlReader();
	}

	@Bean
	public JdbcService jdbcService() {
		return new DefaultJdbcService();
	}

	@Bean
	public Map<String, LocationSupplierSourceBean> defaultExtensionMappings() {
		Project project = project();

		SqlLocationSupplier sls = new SqlLocationSupplier();
		sls.setReader(sqlReader());
		sls.setEncoding(project.getEncoding());

		LocationSupplierSourceBean lssb = new LocationSupplierSourceBean();
		lssb.setSupplierClass(SqlLocationSupplier.class);
		lssb.setSupplierInstance(sls);

		Map<String, LocationSupplierSourceBean> map = new HashMap<String, LocationSupplierSourceBean>();
		map.put("sql", lssb);
		return map;
	}

}
